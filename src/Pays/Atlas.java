package Pays;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class Atlas extends Frame implements ActionListener {

//    déclaration
    Label lblNom, lblCapitale, lblPopulation, lblContinent;
    TextField txtNom, txtCapitale, txtPopulation, txtContinent;
    Button btnAjouter, btnModifier, btnSupprimer, btnValider, btnFirst, btnLast, btnNext, btnPrev;
    Panel panel1, panel2, panel3;

    private ArrayList<Pays> paysList = new ArrayList<Pays>();
    private int index = 0;


//méthode
    private void charger() {
        String file = "pays.txt";
        String line;
        try {
            BufferedReader infoPays = new BufferedReader(new FileReader(file));

            while ((line = infoPays.readLine()) != null) {
                StringTokenizer st = new StringTokenizer(line,";");  //split
                Pays p = new Pays();
                p.setNom(st.nextToken());
                p.setCapitale(st.nextToken());
                p.setPopulation(Long.parseLong(st.nextToken()));
                p.setContinent(st.nextToken());
                paysList.add(p);
            }
            infoPays.close();
        } catch (IOException e) {
            System.err.println("Erreur de fichier:\n"+e);
        }catch(NumberFormatException e){
            System.err.println("Format de nombre incorrect\n" +e);
        }
    }

    private void afficher(){
         for (int i = 0; i < paysList.size() && i < 4; i++){
            Pays p = paysList.get(index);
            txtNom.setText(p.getNom());
            txtCapitale.setText(p.getCapitale());
            txtPopulation.setText(Long.toString(p.getPopulation()));
            txtContinent.setText(p.getContinent());
         }
    }

    public Atlas() {
        super("Atlas");
        panel1 = new Panel();
        panel1.setLayout(new GridLayout(4, 2));

        panel2 = new Panel();
        panel2.setLayout(new FlowLayout());

        panel3 = new Panel();
        panel3.setLayout(new FlowLayout());

        lblNom = new Label("Nom : ");
        lblCapitale = new Label("Capitale : ");
        lblPopulation = new Label("Population : ");
        lblContinent = new Label("Continent : ");

        txtNom = new TextField("", 10);
        txtNom.setEditable(false);
        txtCapitale = new TextField("", 10);
        txtCapitale.setEditable(false);
        txtPopulation = new TextField("", 10);
        txtPopulation.setEditable(false);
        txtContinent = new TextField("", 10);
        txtContinent.setEditable(false);

        btnAjouter = new Button("Ajouter");
        btnAjouter.setBackground(Color.GREEN);

        btnModifier = new Button("Modifier");
        btnModifier.setForeground(Color.white);
        btnModifier.setBackground(Color.BLUE);

        btnValider = new Button("Valider");
        btnValider.setBackground(Color.YELLOW);

        btnSupprimer = new Button("Supprimer");
        btnSupprimer.setForeground(Color.white);
        btnSupprimer.setBackground(Color.RED);

        btnFirst = new Button("|<");
        btnLast = new Button(">|");
        btnPrev = new Button("<<");
        btnNext = new Button(">>");





        panel3.add(btnAjouter);
        panel3.add(btnModifier);
        panel3.add(btnValider);
        panel3.add(btnSupprimer);

        panel1.add(lblNom);
        panel1.add(txtNom);
        panel1.add(lblCapitale);
        panel1.add(txtCapitale);
        panel1.add(lblPopulation);
        panel1.add(txtPopulation);
        panel1.add(lblContinent);
        panel1.add(txtContinent);

        panel2.add(btnFirst);
        panel2.add(btnPrev);
        panel2.add(btnNext);
        panel2.add(btnLast);

        add(panel2, "South");
        add(panel3, "North");
        add(panel1, "West");

        this.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                System.exit(0);
            }
        });

        btnFirst.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ev) {
                index = 0;
                afficher();
            }
        });

        btnLast.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ev) {
                index = paysList.size()-1;
                afficher();
            }
        });

        btnNext.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                index ++;
                afficher();
            }
        });

        btnPrev.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                index--;
                afficher();
            }
        });


        setVisible(true);
        setSize(300, 180);
        charger();
        afficher();

    }

    @Override
    public void actionPerformed(ActionEvent e) {

    }
}
