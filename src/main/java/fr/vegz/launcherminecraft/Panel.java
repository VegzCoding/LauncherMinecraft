package fr.vegz.launcherminecraft;

import fr.theshark34.swinger.event.SwingerEvent;
import fr.theshark34.swinger.event.SwingerEventListener;
import fr.theshark34.swinger.textured.STexturedButton;
import fr.vegz.launcherminecraft.utils.MicrosoftThread;

import javax.swing.*;
import java.awt.*;
import java.io.IOException;

import static fr.vegz.launcherminecraft.Frame.*;

public class Panel extends JPanel implements SwingerEventListener {

    private Image background  = getImage("Launcher.png");
    private STexturedButton play = new STexturedButton(getBufferedImage("bouton.png"), getBufferedImage("bouton.png"));
    private STexturedButton microsoft = new STexturedButton(getBufferedImage("microsoft.png"), getBufferedImage("microsoft.png"));

    public Panel() throws IOException {
        this.setLayout(null);

        play.setBounds(369, 214);
        play.setLocation(495,447);
        play.addEventListener(this);
        this.add(play);

        microsoft.setBounds(100, 100);
        microsoft.setLocation(1250, 10);
        microsoft.addEventListener(this);
        this.add(microsoft);
    }

    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);

        g.drawImage(background, 0, 0, this.getWidth(), this.getHeight(), this);
    }

    @Override
    public void onEvent(SwingerEvent swingerEvent) {
        if (swingerEvent.getSource() == microsoft) {
            Thread t = new Thread(new MicrosoftThread());
            t.start();
        } else if (swingerEvent.getSource() == play) {
            try {
                Launcher.update();
            } catch (Exception e) {
                Launcher.getReporter().catchError(e,"Impossible de mettre à jour le launcher !");
            }
            try {
                Launcher.launch();
            } catch (Exception e) {
                Launcher.getReporter().catchError(e, "Impossible de lancer le launcher !");
            }
        }
    }
}
