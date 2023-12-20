package thegame.app.numgame;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Text;

import java.util.Random;

public class HelloController {
    @FXML
    public Text text1;
    @FXML
    public Text text2;
    @FXML
    public Text text3;
    @FXML
    public Text text4;

    @FXML
    public Label finish;
    @FXML
    public Button but1;
    @FXML
    public Button but2;
    @FXML
    public Button but3;
    @FXML
    public Button but4;
    @FXML
    public Button start;

    @FXML
    public Rectangle box1;
    @FXML
    public Rectangle box2;
    @FXML
    public Rectangle box3;
    @FXML
    public Rectangle box4;
    @FXML
    public Text move;
    Text[] texts;
    Button[] buttons;
    Rectangle[] boxes;

    UserClass user = new UserClass();

    @FXML
    public void initialize() {
        texts = new Text[]{text1, text2, text3, text4};
        buttons = new Button[]{but1, but2, but3, but4};
        boxes = new Rectangle[]{box1, box2, box3, box4};
    }

    private void resetButton() {
        but1.setDisable(false);
        but1.setOnAction(event -> hit1());
        but2.setDisable(false);
        but2.setOnAction(event -> hit2());
        but3.setDisable(false);
        but3.setOnAction(event -> hit3());
        but4.setDisable(false);
        but4.setOnAction(event -> hit4());
    }

    public void start(){
        for (Rectangle box : boxes) {
            box.setFill(Color.web("#3795e8"));
            box.setOpacity(0.5);
        }
        for (Text text : texts) {
            text.setText("1");
        }
        resetButton();

        Random random = new Random();
        int[] randoms = new int[4];
        for (int i = 0; i < randoms.length; i++) {
            randoms[i] = random.nextInt(4);
        }
        do {
            for (int i = 0; i < buttons.length; i++) {
                for (int j = 0; j < randoms[i]; j++) {
                    buttons[i].fire();
                }
            }
        } while (text1.getText().equals(text2.getText()) && text2.getText().equals(text3.getText()) && text3.getText().equals(text4.getText()));
        user.setCurrentMove(0);
        start.setText("ReRoll");
        finish.setText(null);
    }


    //    game logic
    private void win() {
        finish.setText("You Won!");
        start.setText("RePlay");
        for (Rectangle box : boxes) {
            box.setFill(Color.RED);
        }
        but1.setDisable(true);
        but1.setOnAction(null);
        but2.setDisable(true);
        but2.setOnAction(null);
        but3.setDisable(true);
        but3.setOnAction(null);
        but4.setDisable(true);
        but4.setOnAction(null);
        if(user.getCurrentMove() < user.getLeastMove()) {
            user.setLeastMove(user.getCurrentMove());
            move.setText("Least move:" + user.getLeastMove());
        }
    }
    private void hit(int index) {
        index -= 1;
//        boxes[index].setOpacity(boxes[index].getOpacity() + 0.2);
        switch (texts[index].getText()) {
            case "1", "2", "3" -> {
                try {
                    int currentValue = Integer.parseInt(texts[index].getText());
                    int newValue = currentValue + 1;
                    texts[index].setText(Integer.toString(newValue));
                } catch (NumberFormatException e) {
                    System.out.println("NumberFormatException: " + e);
                }
            }
            case "4" -> {
                texts[index].setText("1");
            }
        }
    }
    private void check() {
        if (text1.getText().equals(text2.getText()) && text2.getText().equals(text3.getText()) && text3.getText().equals(text4.getText())) {
            win();
        }
    }

    //    hit1/2/3/4
    public void hit1() {
        user.setCurrentMove(user.getCurrentMove()+1);
        hit(1);
        hit(2);

        check();
    }
    public void hit2() {
        user.setCurrentMove(user.getCurrentMove()+1);
        hit(1);
        hit(2);
        hit(3);

        check();
    }
    public void hit3() {
        user.setCurrentMove(user.getCurrentMove()+1);
        hit(2);
        hit(3);
        hit(4);

        check();
    }
    public void hit4() {
        user.setCurrentMove(user.getCurrentMove()+1);
        hit(3);
        hit(4);

        check();
    }

}