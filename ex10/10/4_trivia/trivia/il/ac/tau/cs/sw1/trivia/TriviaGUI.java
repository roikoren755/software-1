package il.ac.tau.cs.sw1.trivia;

import org.eclipse.swt.SWT;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.events.SelectionListener;
import org.eclipse.swt.graphics.Font;
import org.eclipse.swt.graphics.FontData;
import org.eclipse.swt.graphics.Point;
import org.eclipse.swt.graphics.Rectangle;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Text;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

public class TriviaGUI {

	private static final int MAX_ERRORS = 3;
	// Currently visible UI elements.
	Label instructionLabel;
	Label questionLabel;
	private Shell shell;
	private Label scoreLabel;
	private Composite questionPanel;
	private Label startupMessageLabel;
	private Font boldFont;
	private String lastAnswer;
	private List<Button> answerButtons;
	private Button passButton;
	private Button fiftyFiftyButton;

	// Game variables
	private int score;
	private int correctAnswers;
	private ArrayList<ArrayList<String>> riddles;
	private int errors;
	private Iterator<ArrayList<String>> iter;
	private ArrayList<String> next;
	private boolean passUsed;
	private boolean fiftyFiftyUsed;

	public void open() {
		createShell();
		runApplication();
	}

	/**
	 * Creates the widgets of the application main window
	 */
	private void createShell() {
		Display display = Display.getDefault();
		shell = new Shell(display);
		shell.setText("Trivia");

		// window style
		Rectangle monitor_bounds = shell.getMonitor().getBounds();
		shell.setSize(new Point(monitor_bounds.width / 3,
				monitor_bounds.height / 4));
		shell.setLayout(new GridLayout());

		FontData fontData = new FontData();
		fontData.setStyle(SWT.BOLD);
		boldFont = new Font(shell.getDisplay(), fontData);

		// create window panels
		createFileLoadingPanel();
		createScorePanel();
		createQuestionPanel();
	}

	/**
	 * Creates the widgets of the form for trivia file selection
	 */
	private void createFileLoadingPanel() {
		final Composite fileSelection = new Composite(shell, SWT.NULL);
		fileSelection.setLayoutData(GUIUtils.createFillGridData(1));
		fileSelection.setLayout(new GridLayout(4, false));

		final Label label = new Label(fileSelection, SWT.NONE);
		label.setText("Enter trivia file path: ");

		// text field to enter the file path
		final Text filePathField = new Text(fileSelection, SWT.SINGLE
				| SWT.BORDER);
		filePathField.setLayoutData(GUIUtils.createFillGridData(1));

		// "Browse" button
		final Button browseButton = new Button(fileSelection, SWT.PUSH);
		browseButton.setText("Browse");
		browseButton.addSelectionListener(new SelectionListener() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				String filePath = GUIUtils.getFilePathFromFileDialog(shell);
				if (filePath == null)
					return;
				filePathField.setText(filePath);
			}

			@Override
			public void widgetDefaultSelected(SelectionEvent e) {
			}
		});

		// "Play!" button
		final Button playButton = new Button(fileSelection, SWT.PUSH);
		playButton.setText("Play!");
		playButton.addSelectionListener(new SelectionListener() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				updateScore(0);
				errors = 0;
				passUsed = false;
				fiftyFiftyUsed = false;
				lastAnswer = "";
				riddles = new ArrayList<ArrayList<String>>();
				String path = filePathField.getText();
				try {
					BufferedReader reader = new BufferedReader(new FileReader(path));
					for (String line = reader.readLine(); line != null; line = reader.readLine()) {
						String[] splitLine = line.split("\t");
						ArrayList<String> arrayList = new ArrayList<String>();
						for (String word : splitLine)
							arrayList.add(word);
						riddles.add(arrayList);
					}
					reader.close();
					Collections.shuffle(riddles);
					iter = riddles.iterator();
					showNextQuestion();
				} catch (FileNotFoundException exception) {
					GUIUtils.showErrorDialog(shell, "File not found!\n" + path);
					return;
				} catch (IOException exception) {
					GUIUtils.showErrorDialog(shell, "Error reading from file!");
					return;
				}
			}

			@Override
			public void widgetDefaultSelected(SelectionEvent e) {
			}
		});
	}

	/**
	 * Creates the panel that displays the current score
	 */
	private void createScorePanel() {
		Composite scorePanel = new Composite(shell, SWT.BORDER);
		scorePanel.setLayoutData(GUIUtils.createFillGridData(1));
		scorePanel.setLayout(new GridLayout(2, false));

		final Label label = new Label(scorePanel, SWT.NONE);
		label.setText("Total score: ");

		// The label which displays the score; initially empty
		scoreLabel = new Label(scorePanel, SWT.NONE);
		scoreLabel.setLayoutData(GUIUtils.createFillGridData(1));
	}

	/**
	 * Creates the panel that displays the questions, as soon as the game
	 * starts. See the updateQuestionPanel for creating the question and answer
	 * buttons
	 */
	private void createQuestionPanel() {
		questionPanel = new Composite(shell, SWT.BORDER);
		questionPanel.setLayoutData(new GridData(GridData.FILL, GridData.FILL,
				true, true));
		questionPanel.setLayout(new GridLayout(2, true));

		// Initially, only displays a message
		startupMessageLabel = new Label(questionPanel, SWT.NONE);
		startupMessageLabel.setText("No question to display, yet.");
		startupMessageLabel.setLayoutData(GUIUtils.createFillGridData(2));
	}

	/**
	 * Serves to display the question and answer buttons
	 */
	private void updateQuestionPanel(String question, List<String> answers) {
		// Save current list of answers.
		String correctAnswer = answers.get(0);
		Collections.shuffle(answers);

		// clear the question panel
		Control[] children = questionPanel.getChildren();
		for (Control control : children) {
			control.dispose();
		}

		// create the instruction label
		instructionLabel = new Label(questionPanel, SWT.CENTER | SWT.WRAP);
		instructionLabel.setText(lastAnswer + "Answer the following question:");
		instructionLabel.setLayoutData(GUIUtils.createFillGridData(2));

		// create the question label
		questionLabel = new Label(questionPanel, SWT.CENTER | SWT.WRAP);
		questionLabel.setText(question);
		questionLabel.setFont(boldFont);
		questionLabel.setLayoutData(GUIUtils.createFillGridData(2));

		// create the answer buttons
		answerButtons = new LinkedList<Button>();
		for (int i = 0; i < 4; i++) {
			Button answerButton = new Button(questionPanel, SWT.PUSH | SWT.WRAP);
			answerButton.setText(answers.get(i));
			GridData answerLayoutData = GUIUtils.createFillGridData(1);
			answerLayoutData.verticalAlignment = SWT.FILL;
			answerButton.setLayoutData(answerLayoutData);
			answerButton.addSelectionListener(new SelectionListener() {
				@Override
				public void widgetSelected(SelectionEvent e) {
					if (answerButton.getText().equals(correctAnswer)) {
						correctAnswers++;
						errors = 0;
						updateScore(score + 3);
						showNextQuestion();
					} else {
						updateScore(score - 2);
						errors++;
						if (errors == MAX_ERRORS)
							endGame("You Lose!", "Unfortunately, you lost.\n"
									+ "You did manage to score " + score + " points though,\n"
									+ "correctly answering " + correctAnswers + "questions."
									+ "\nGo read some more and try again!");
						else
							showNextQuestion();
					}
				}

				@Override
				public void widgetDefaultSelected(SelectionEvent e) {
				}
			});
			answerButtons.add(answerButton);
		}

		// create the "Pass" button to skip a question
		passButton = new Button(questionPanel, SWT.PUSH);
		passButton.setText("Pass");
		GridData data = new GridData(GridData.END, GridData.CENTER, true,
				false);
		data.horizontalSpan = 1;
		passButton.setLayoutData(data);
		passButton.addSelectionListener(new SelectionListener() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				if (passUsed)
					updateScore(score - 1);
				passUsed = true;
				showNextQuestion();
			}

			@Override
			public void widgetDefaultSelected(SelectionEvent e) {
			}
		});

		// create the "50-50" button to show fewer answer options
		fiftyFiftyButton = new Button(questionPanel, SWT.PUSH);
		fiftyFiftyButton.setText("50-50");
		data = new GridData(GridData.BEGINNING, GridData.CENTER, true,
				false);
		data.horizontalSpan = 1;
		fiftyFiftyButton.setLayoutData(data);
		fiftyFiftyButton.addSelectionListener(new SelectionListener() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				if (fiftyFiftyUsed)
					updateScore(score - 1);
				fiftyFiftyUsed = true;
				Collections.shuffle(answerButtons);
				int disabled = 0;
				for (Button answerButton : answerButtons) {
					if (!answerButton.getText().equals(correctAnswer) && disabled < 2) {
						answerButton.setEnabled(false);
						disabled++;
					}
				}
			}

			@Override
			public void widgetDefaultSelected(SelectionEvent e) {
			}
		});

		// two operations to make the new widgets display properly
		questionPanel.pack();
		questionPanel.getParent().layout();
	}

	private void endGame(String title, String message) {
		Control[] children = questionPanel.getChildren();
		for (Control control : children) {
			control.dispose();
		}
		createQuestionPanel();
		GUIUtils.showInfoDialog(shell, title, message);
	}

	private void showNextQuestion() {
		if (iter.hasNext()) {
			next = iter.next();
			updateQuestionPanel(next.get(0), next.subList(1, 5));
			passButton.setEnabled(!passUsed || score > 0);
			fiftyFiftyButton.setEnabled(!fiftyFiftyUsed || score > 0);
		} else
			endGame("Congratulations!", "Very well done!\nYou scored "
					+ score + " points and answered " + correctAnswers +
					" questions correctly!\nYou have won!");
	}

	private void updateScore(int score) {
		this.score = score;
		scoreLabel.setText(String.valueOf(score));
	}

	/**
	 * Opens the main window and executes the event loop of the application
	 */
	private void runApplication() {
		shell.open();
		Display display = shell.getDisplay();
		while (!shell.isDisposed()) {
			if (!display.readAndDispatch())
				display.sleep();
		}
		display.dispose();
		boldFont.dispose();
	}
}
