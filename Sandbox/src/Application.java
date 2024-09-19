import nl.saxion.app.SaxionApp;

import java.util.ArrayList;
import java.util.List;

public class Application implements Runnable {

    public int counter = 0;
    public int wrongAnswers = 0;
    public String word;
    public boolean[] marker = new boolean[99];
    public static void main(String[] args) {
        SaxionApp.start(new Application(), 1024, 768);

    }

    public void run() {
        Boolean gameOver = false;


        SaxionApp.print("please enter a 4 letter word you want to use: ");
        word = SaxionApp.readString();

        for (int i = 0; i < word.length(); i++) {

        }
        textLines();
        draw();
        while (!gameOver) {

            SaxionApp.print("Whats your Guess?:");
            String guess = SaxionApp.readString();
            gameOver = wincon(guess);
            if(wrongAnswers >= 12){gameOver = true;}
            draw();
        }
        if (counter == word.length()) {
            SaxionApp.print("You won!");
        } else {
            SaxionApp.print("You lost!");
        }
        //SaxionApp.print("UwU code borked UwU");
    }

    public void Hangman() {
        //adds pieces of the hangman to an array and then extracts based on the amount of wrong answers.
        // i am aware this is really unnecessary but i have  never worked with runnable list so yea.
        List<Runnable> DrawingHangman = new ArrayList<>();
        DrawingHangman.add(() -> SaxionApp.drawLine(100, 600, 300, 600));   // base
        DrawingHangman.add(() -> SaxionApp.drawLine(200, 600, 200, 200));   // vertical pole
        DrawingHangman.add(() -> SaxionApp.drawLine(200, 200, 400, 200));   // horizontal pole
        DrawingHangman.add(() -> SaxionApp.drawLine(200, 250, 250, 200));   // support beam
        DrawingHangman.add(() -> SaxionApp.drawLine(400, 200, 400, 250));   // rope
        DrawingHangman.add(() -> SaxionApp.drawCircle(400, 300, 50));       // head
        DrawingHangman.add(() -> SaxionApp.drawLine(400, 350, 400, 500));   // body
        DrawingHangman.add(() -> SaxionApp.drawLine(400, 500, 450, 550));   // right foot
        DrawingHangman.add(() -> SaxionApp.drawLine(400, 500, 350, 550));   // left foot
        DrawingHangman.add(() -> SaxionApp.drawLine(400, 400, 450, 450));   // right arm
        DrawingHangman.add(() -> SaxionApp.drawLine(400, 400, 350, 450));// left arm
        for (int i = 0; i < wrongAnswers; i++) {
            DrawingHangman.get(i).run();

        }
    }

    public void textLines() {
        int letterCount = 0;
        for (int i = 0; i < word.length(); i++) {
            SaxionApp.drawLine(500 + (letterCount * 25 + 25), 600, 510 + (letterCount * 25), 600);
            letterCount++;
        }
    }

    public Boolean wincon(String guess) {
        if (word.contains(guess)) {
            counter++;SaxionApp.printLine(counter);marker[word.indexOf(guess)] = true;
        } else {
            wrongAnswers++;
        }
        return counter >= word.length();
    }

    public void draw() {

        Hangman();
        textLines();
        drawLetters();
    }

    public void drawLetters() {
        int letterCount = 0;
        for (int i = 0; i < word.length(); i++) {
            if(marker[i]) {
        SaxionApp.drawText(""+word.charAt(i),487+(i*25+25),580,20);}
        }
        //SaxionApp.drawLine(500+(letterCount*25+25), 600,510+(letterCount*25),600);
    }

}
//todo make repeated letters work
//todo make guesses only work once