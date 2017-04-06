
import java.util.ArrayList;
import javax.swing.Timer;

/**
 * Cette classe crée un JFrame qui contient l'univers de jeu. Elle crée tous 
 * les acteurs nécessaires au jeu. Elle initialise le KeyListener nécessaire aux
 * mouvements de la grenouille. Elle continent tous les méthodes nécessaires 
 * pour le bon fonctionnement du jeu.
 * 
 * @author Julien
 */
public class World extends javax.swing.JFrame {

	private static final long serialVersionUID = -4446533917653613543L;
	
	private final int UI_UPDATE_TIME  = 200;
	private final int GAME_TIME = 30000;
	
	private double speedCoeff;
    private int life;
    private int score;
    private int level;
    private Timer timerMoveActor;
    private ActorMoveListener actorMoveListener;
    private int gameDuration;
    private KeyEventListener keyEventListener;
    /**
     * Creates new form World
     */
    public World() {
        this.life = 3;
        this.score = 0;
        this.level = 1;
        this.gameDuration = GAME_TIME;
        
        this.setSpeedCoeff();
        this.initComponents();
        
        this.keyEventListener = new KeyEventListener(this, frog);
        this.addKeyListener(this.keyEventListener);
        
        this.start();
    }
    /**
     * Methode qui interrompt le jeu avant de commencer, apres avoir reussi un niveau ou avant de recommencer quand une vie est perdue
     * @param millis nombre de millisecondes pendant lequel le programme est interrompu
     */
    private void sleep(int millis){
    	try {
    	    Thread.sleep(millis);                 //1000 milliseconds is one second.
    	} catch(InterruptedException ex) {
    	    Thread.currentThread().interrupt();
    	}
    }
    
    /**
     * Methode qui debute le jeu 
     */
    public void start(){
		
		this.sleep(2000);
		
		this.gameDuration = GAME_TIME;
		this.frog.setBounds(280, 540, 40, 40);
		
		//Timer pour bouger les acteurs
		this.actorMoveListener = new ActorMoveListener(this);
		this.timerMoveActor = new Timer(UI_UPDATE_TIME, this.actorMoveListener);
		this.timerMoveActor.start();
		
		//pour initialiser le texte des labels
		scoreLabel.setText("Score : " + this.score);
		levelLabel.setText("Level : " + this.level);
		lifeLabel.setText("Vie : "+ this.life);
		timeLabel.setText("Time : " + (this.gameDuration / 1000));
	}

    /**
     * Methode qui determine la vitesse des obstacles
     */
	private void setSpeedCoeff(){
		this.speedCoeff = (Math.log(this.level) * 1.5) + 1;
	}
	
	/**
	 * Methode qui est appelee pour arreter le timer qui fait bouger les obstacles
	 */
	private void cleanTimer(){
		this.timerMoveActor.stop();
	}
	
	/**
	 * Methode qui est appele quand le temps est termine, 
	 * perd soit une vie, si n'a plus de vie c'est gameOver sinon le jeu peut recommence
	 */
	public void timeOver() {
		this.cleanTimer();
		this.life--;
		if(this.life == 0){
			this.gameOver();
		}
		else{
			this.start();
		}	
	}
	
	/**
	 * Methode qui est appelee quand la grenouille a ete en contact avec un acteur,
	 * perd soit une vie, si n'a plus de vie c'est gameOver sinon le jeu peut recommence
	 */
	public void frogFault(){
		this.cleanTimer();
		this.life--;
		if(this.life == 0){
			this.gameOver();
		}
		else{
			this.start();
		}	
	}
	
	/**
	 * Methode qui permet de bouger les acteurs (voitures, riviere et camion)
	 * grace au timer timerMoveActor
	 */
	public void moveActors(){
		for(int i = 0; i < this.actors.size(); i++){
			this.actors.get(i).act();
		}
		
		this.validateFrog();
		
		//reset timer for next ui refresh
		this.timerMoveActor.setDelay(UI_UPDATE_TIME);
		this.gameDuration -= UI_UPDATE_TIME;
		timeLabel.setText("Time : " + (this.gameDuration / 1000));
		if(this.gameDuration <= 0)
		{
			timeOver();
		}

	}
	
	/**
	 * Methode qui verifie si la grenouille est en contact ou non avec les autres acteurs
	 */
	public void validateFrog(){
		int[][] frogLocation = this.frog.getLocat(); 
		int[][] actLocation;
		boolean inX = false; // si entre les 2 points
		
		if(frogLocation[0][1] == 0) {
                    this.gameCompleted();
		}
		
		for(int i = 0; i < actors.size(); i++){
			inX = false;
			actLocation = actors.get(i).getLocat();
			
			
			if(frogLocation[0][0] >= actLocation[0][0] && 
			frogLocation[0][0] <= actLocation[1][0]) {
				inX = true;
				
			}
			else if(frogLocation[1][0] >= actLocation[0][0] &&
					frogLocation[1][0] <= actLocation[1][0]){
				inX = true;
				
			}
			
			if(inX && 
					frogLocation[0][1] >= actLocation[0][1] &&
					frogLocation[0][1] <= actLocation[2][1]){
				frogFault();
			}
			else if(inX && 
					frogLocation[2][1] >= actLocation[0][1] &&
					frogLocation[2][1] <= actLocation[2][1]){
				frogFault();
			}
		}
	}
		/**
		 * Methode pour avoir le score
		 * @return la variable score
		 */
	public int getScore(){
		return this.score;
	}
	
	/**
	 * Methode pour incrementer le niveau du jeu et augmenter la vitesse des acteurs
	 */
	public void levelUp(){
		this.level++;
        this.setSpeedCoeff();
        
        for(int i = 0; i < actors.size(); i++) {
        	actors.get(i).setSpeed(speedCoeff);
        }
        
		this.start();
	}
	/**
	 * Methode appelee quand toutes les vies sont a 0 et creer le Game Over panel
	 */
	public void gameOver(){
        GameOver go = new GameOver(this.score);
        go.setVisible(true);
        this.setVisible(false);
        this.dispose();
		this.lifeLabel.setText("Vie : 0");
	}

	/**
	 * Methode pour calculer le score quand le niveau est termine
	 */
	public void gameCompleted(){
            this.cleanTimer();
		double tempScore = (double)(GAME_TIME - this.gameDuration)/(double)GAME_TIME;
		this.score += tempScore * 100 * speedCoeff;
		this.levelUp();
    }  
	
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        lifeLabel = new javax.swing.JLabel();
        scoreLabel = new javax.swing.JLabel();
        timeLabel = new javax.swing.JLabel();
        levelLabel = new javax.swing.JLabel();
        jLayeredPane1 = new javax.swing.JLayeredPane();
        river14 = new River(speedCoeff,false);
        river13 = new River(speedCoeff,false);
        river12 = new River(speedCoeff,false);
        river11 = new River(speedCoeff,true);
        river10 = new River(speedCoeff,true);
        river9 = new River(speedCoeff,true);
        river8 = new River(speedCoeff,true);
        river7 = new River(speedCoeff,false);
        river6 = new River(speedCoeff,false);
        river5 = new River(speedCoeff,true);
        river4 = new River(speedCoeff,true);
        river3 = new River(speedCoeff,false);
        river2 = new River(speedCoeff,false);
        river1 = new River(speedCoeff,false);
        car7 = new Car(speedCoeff,true);
        car8 = new Car(speedCoeff,true);
        car9 = new Car(speedCoeff,true);
        truck3 = new Truck(speedCoeff,false);
        truck4 = new Truck(speedCoeff,false);
        car6 = new Car(speedCoeff,true);
        car5 = new Car(speedCoeff,true);
        car4 = new Car(speedCoeff,true);
        car1 = new Car(speedCoeff,true);
        car2 = new Car(speedCoeff,true);
        car3 = new Car(speedCoeff,true);
        truck1 = new Truck(speedCoeff,false);
        truck2 = new Truck(speedCoeff,false);
        frog = new Frog();
        map = new javax.swing.JLabel();
    	this.actors= new ArrayList<Actor>();
    	actors.add(car1);
    	actors.add(car2);
    	actors.add(car3);
    	actors.add(car4);
    	actors.add(car5);
    	actors.add(car6);
    	actors.add(car7);
    	actors.add(car8);
    	actors.add(car9);
    	actors.add(truck1);
    	actors.add(truck2);
    	actors.add(truck3);
    	actors.add(truck4);
    	actors.add(river1);
    	actors.add(river2);
    	actors.add(river3);
    	actors.add(river4);
    	actors.add(river5);
    	actors.add(river6);
    	actors.add(river7);
    	actors.add(river8);
    	actors.add(river9);
    	actors.add(river10);
    	actors.add(river11);
    	actors.add(river12);
    	actors.add(river13);
    	actors.add(river14);
    	
        
        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setBackground(new java.awt.Color(0, 0, 0));
        setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        setForeground(new java.awt.Color(0, 0, 0));
        setPreferredSize(new java.awt.Dimension(600, 650));
        setResizable(false);

        lifeLabel.setBackground(new java.awt.Color(255, 255, 255));
        lifeLabel.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        lifeLabel.setText("Vie : "+life);
        lifeLabel.setToolTipText("");

        scoreLabel.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        scoreLabel.setText("Score : " + score);
        scoreLabel.setToolTipText("");

        timeLabel.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        timeLabel.setText("Time : ");

        levelLabel.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        levelLabel.setText("Level : "+level);

        river14.setBackground(new java.awt.Color(62, 179, 255));
        river14.setOpaque(true);
        jLayeredPane1.add(river14);
        river14.setBounds(480, 45, 100, 40);

        river13.setBackground(new java.awt.Color(62, 179, 255));
        river13.setOpaque(true);
        jLayeredPane1.add(river13);
        river13.setBounds(280, 45, 100, 40);

        river12.setBackground(new java.awt.Color(62, 179, 255));
        river12.setOpaque(true);
        jLayeredPane1.add(river12);
        river12.setBounds(60, 45, 100, 40);

        river11.setBackground(new java.awt.Color(62, 179, 255));
        river11.setOpaque(true);
        jLayeredPane1.add(river11);
        river11.setBounds(480, 90, 50, 40);

        river10.setBackground(new java.awt.Color(62, 179, 255));
        river10.setOpaque(true);
        jLayeredPane1.add(river10);
        river10.setBounds(320, 90, 50, 40);

        river9.setBackground(new java.awt.Color(62, 179, 255));
        river9.setOpaque(true);
        jLayeredPane1.add(river9);
        river9.setBounds(160, 90, 50, 40);

        river8.setBackground(new java.awt.Color(62, 179, 255));
        river8.setOpaque(true);
        jLayeredPane1.add(river8);
        river8.setBounds(0, 90, 50, 40);

        river7.setBackground(new java.awt.Color(62, 179, 255));
        river7.setOpaque(true);
        jLayeredPane1.add(river7);
        river7.setBounds(390, 135, 100, 40);

        river6.setBackground(new java.awt.Color(62, 179, 255));
        river6.setOpaque(true);
        jLayeredPane1.add(river6);
        river6.setBounds(90, 135, 100, 40);

        river5.setBackground(new java.awt.Color(62, 179, 255));
        river5.setOpaque(true);
        jLayeredPane1.add(river5);
        river5.setBounds(300, 180, 160, 40);

        river4.setBackground(new java.awt.Color(62, 179, 255));
        river4.setOpaque(true);
        jLayeredPane1.add(river4);
        river4.setBounds(0, 180, 160, 40);

        river3.setBackground(new java.awt.Color(62, 179, 255));
        river3.setOpaque(true);
        jLayeredPane1.add(river3);
        river3.setBounds(430, 225, 60, 40);

        river2.setBackground(new java.awt.Color(62, 179, 255));
        river2.setOpaque(true);
        jLayeredPane1.add(river2);
        river2.setBounds(230, 225, 60, 40);

        river1.setBackground(new java.awt.Color(62, 179, 255));
        river1.setOpaque(true);
        jLayeredPane1.add(river1);
        river1.setBounds(30, 225, 60, 40);

        car7.setIcon(new javax.swing.ImageIcon(getClass().getResource("/frogger_car_R.png"))); // NOI18N
        jLayeredPane1.add(car7);
        car7.setBounds(120, 315, 40, 40);

        car8.setIcon(new javax.swing.ImageIcon(getClass().getResource("/frogger_car_R.png"))); // NOI18N
        jLayeredPane1.add(car8);
        car8.setBounds(350, 315, 40, 40);

        car9.setIcon(new javax.swing.ImageIcon(getClass().getResource("/frogger_car_R.png"))); // NOI18N
        jLayeredPane1.add(car9);
        car9.setBounds(560, 315, 40, 40);

        truck3.setIcon(new javax.swing.ImageIcon(getClass().getResource("/frogger_truck_L.png"))); // NOI18N
        jLayeredPane1.add(truck3);
        truck3.setBounds(40, 360, 80, 40);

        truck4.setIcon(new javax.swing.ImageIcon(getClass().getResource("/frogger_truck_L.png"))); // NOI18N
        jLayeredPane1.add(truck4);
        truck4.setBounds(330, 360, 80, 40);

        car6.setIcon(new javax.swing.ImageIcon(getClass().getResource("/frogger_car_R.png"))); // NOI18N
        jLayeredPane1.add(car6);
        car6.setBounds(0, 405, 40, 40);

        car5.setIcon(new javax.swing.ImageIcon(getClass().getResource("/frogger_car_R.png"))); // NOI18N
        jLayeredPane1.add(car5);
        car5.setBounds(230, 405, 40, 40);

        car4.setIcon(new javax.swing.ImageIcon(getClass().getResource("/frogger_car_R.png"))); // NOI18N
        jLayeredPane1.add(car4);
        car4.setBounds(470, 405, 40, 40);

        car1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/frogger_car_R.png"))); // NOI18N
        car1.setPreferredSize(new java.awt.Dimension(40, 40));
        jLayeredPane1.add(car1);
        car1.setBounds(510, 495, 40, 40);

        car2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/frogger_car_R.png"))); // NOI18N
        car2.setPreferredSize(new java.awt.Dimension(40, 40));
        jLayeredPane1.add(car2);
        car2.setBounds(60, 495, 40, 40);

        car3.setIcon(new javax.swing.ImageIcon(getClass().getResource("/frogger_car_R.png"))); // NOI18N
        jLayeredPane1.add(car3);
        car3.setBounds(300, 495, 40, 40);

        truck1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/frogger_truck_L.png"))); // NOI18N
        truck1.setPreferredSize(new java.awt.Dimension(80, 40));
        jLayeredPane1.add(truck1);
        truck1.setBounds(180, 450, 80, 40);

        truck2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/frogger_truck_L.png"))); // NOI18N
        jLayeredPane1.add(truck2);
        truck2.setBounds(440, 450, 80, 40);

        frog.setIcon(new javax.swing.ImageIcon(getClass().getResource("/frogger_frog.png"))); // NOI18N
        frog.setToolTipText("");
        frog.setPreferredSize(new java.awt.Dimension(40, 40));
        jLayeredPane1.add(frog);
        frog.setBounds(280, 540, 40, 40);

        map.setIcon(new javax.swing.ImageIcon(getClass().getResource("/World.png"))); // NOI18N
        jLayeredPane1.add(map);
        map.setBounds(0, 0, 600, 580);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(lifeLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 124, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(levelLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 75, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 55, Short.MAX_VALUE)
                .addComponent(timeLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 102, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(54, 54, 54)
                .addComponent(scoreLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 152, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
            .addComponent(jLayeredPane1)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.CENTER)
                    .addComponent(scoreLabel)
                    .addComponent(timeLabel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(lifeLabel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(levelLabel))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLayeredPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 580, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(World.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(World.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(World.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(World.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new World().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private ArrayList<Actor> actors;
    
    private Car car1;
    private Car car2;
    private Car car3;
    private Car car4;
    private Car car5;
    private Car car6;
    private Car car7;
    private Car car8;
    private Car car9;
    private Frog frog;
    private javax.swing.JLayeredPane jLayeredPane1;
    private javax.swing.JLabel levelLabel;
    private javax.swing.JLabel lifeLabel;
    private javax.swing.JLabel map;
    private River river1;
    private River river10;
    private River river11;
    private River river12;
    private River river13;
    private River river14;
    private River river2;
    private River river3;
    private River river4;
    private River river5;
    private River river6;
    private River river7;
    private River river8;
    private River river9;
    private javax.swing.JLabel scoreLabel;
    private javax.swing.JLabel timeLabel;
    private Truck truck1;
    private Truck truck2;
    private Truck truck3;
    private Truck truck4;
    // End of variables declaration//GEN-END:variables
    
}
