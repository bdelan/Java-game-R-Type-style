/************************************************************************************************
* Clase PantallaJuego. 																			*
* --------------------------------------------------------------------------------------------- *
* En esta clase se dibuja la imágen de fondo del juego, las imágenes de las naves aliada y 		*
* alienígenas, los disparos láser y el manejo de estos segun las cond iciones que se indican en *
* cada caso. 																					*
* <<< Borja Delgado Angulo >>> 																	*
*************************************************************************************************/

// Lista de bibliotecas.
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Rectangle;
import java.awt.Color;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

import java.util.ArrayList;

import javax.swing.ImageIcon;
import javax.swing.JPanel;
import javax.swing.Timer;

// Clase PantallaJuego.
public class PantallaJuego extends JPanel implements ActionListener {
	// Atributos.
	// Variables para las imagenes del fondo y del contador de naves a lienigenas eliminadas.
    Image imagen, contNaves;
    // Variable timer para manejar cada momento de la ejecucion.
	private Timer timer;
	// Variable para utilizar la nave aliada.
	private Aliada aliada;
	// Variable para utilizar las naves alienigenas.
	private Alienigena alienigenaA;
	private Alienigena alienigenaB;

	// Variables rectangulo para manejar colisiones.
    Rectangle rAliada;
    Rectangle rAlienigenaA;
    Rectangle rAlienigenaB;

    // Variable para validar si el laser colisiona con una nave alieni gena.
    public static boolean colision;

    // Variable contador de naves alienigenas eliminadas.
    public int muertes;

    // Variable para controlas el maximo numero de naves a eliminar se gun modo de juego seleccionado.
    public static int maxMuertes;

    // Variable para modificar la imagen contador de naves alienigenas eliminadas.
	public static String alienEliminados;

	// Variable para validar si la tecla Enter puede pulsarse, solo en caso de fin de partida.
	public static boolean desactEnter;

	// Variable auxiliar de inicio de juego nuevo.
    public static boolean vuelta2;

    // Constructor de la clase PantallaJuego.
	public PantallaJuego() {
		// Se recibe la imagen del fondo de pantalla de una imagen externa.
		ImageIcon ii = new ImageIcon(this.getClass().getResource("univ erse2.gif"));
		imagen = ii.getImage();

		// Se recibe la imagen de origen del contador de naves alienig enas eliminadas de una imagen externa.
		ImageIcon iii = new ImageIcon(this.getClass().getResource("x0. jpg"));
		contNaves = iii.getImage();

		// Se añade la opcion de "escuchar" la tecla pulsada en cada m omento mediante la clase interna TAdapter.
		addKeyListener(new TAdapter());
		// Para que un objeto JPanel reciba las notificaciones del tec lado es necesario incluir la siguiente instrucción.
		setFocusable(true);

		// Se activa el color negro por defecto en el fondo de pantalla.
		setBackground(Color.BLACK);

		// Esta opcion dibuja primero en memoria, y luego dibuja todo junto en pantalla.
        setDoubleBuffered(true);

        // Se crea un nuevo objeto Nave Aliada.
		aliada = new Aliada();
		// Se crea un nuevo objeto alienigena tipo A.
		alienigenaA = new Alienigena();
		// Se crea un nuevo objeto alienigena tipo B.
		alienigenaB = new Alienigena();

		// Se inicializa el contador de muertes, y las variable auxili ares de validar tecla enter y de inicio de juego nuevo.
		muertes = 0;
		desactEnter = false;
		vuelta2 = false;

		// Se crea un nuevo timer para el manejo de cada momento de ej ecucion.
		timer = new Timer(4, this);
		timer.start();
	}

	// Método para dibujar en el JPanel todos los elementos que partic ipan en la ejecucion.
	public void paint(Graphics g) {
    	super.paint(g);

    	Graphics2D g2d = (Graphics2D)g;

    	g2d.drawImage(imagen, 0, 0, null);
		g2d.drawImage(contNaves, 650, 20, null);
		g2d.drawImage(aliada.getImage(), aliada.getX(), aliada.getY(), this);
		g2d.drawImage(alienigenaA.getImageA(), alienigenaA.getXA(), alienigenaA.getYA(),this);
		g2d.drawImage(alienigenaB.getImageB(), alienigenaB.getXB(), alienigenaB.getYB(),this);

		// Se crea un array con los lasers creados en el momento y se recorre, dibujandolos y
		// controlando si colisionan con las naves alienigenas.
		// Mediante el parametro rectangulo, manejamos los limites de los disparos laser y las
		// naves alienigenas y si estos "intersectan", se produce la explosion de la nave
		// alienigena. Esto da lugar al sumatorio del contador de naves eliminadas.
		ArrayList lsr = aliada.getLasers();
		for (int i = 0; i < lsr.size(); i++ ) {
			Laser ls = (Laser) lsr.get(i); g2d.drawImage(ls.getImage(), ls.getX(), ls.getY(), this);

			Rectangle rLaser = ls.getBounds();
			Rectangle rAlienigenaA = alienigenaA.getBoundsA();

			if (rLaser.intersects(rAlienigenaA) && alienigenaA.nuevoA) { 
				ImageIcon ii = new ImageIcon(this.getClass().getResource("explosionNAVE.gif"));
				Alienigena.imageA = ii.getImage();

				colision = true;

				alienigenaA.muertoA();

				muertes +=1;

				eliminados();

				ImageIcon iiii = new ImageIcon(this.getClass().getResource(alienEliminados));
				contNaves = iiii.getImage();

				lienigenaA.nuevoA = false;
			}

			Rectangle rAlienigenaB = alienigenaB.getBoundsB();

			if (rLaser.intersects(rAlienigenaB) && alienigenaB.nuevoB){
				ImageIcon iii = new ImageIcon(this.getClass().getResource("explosionNAVE.gif"));
				Alienigena.imageB = iii.getImage();

				colision = true;

				alienigenaB.muertoB();

				muertes +=1;

				eliminados();

				ImageIcon iiiii = new ImageIcon(this.getClass().getResource(alienEliminados));
				contNaves = iiiii.getImage();

				alienigenaB.nuevoB = false;
			}
		}

		// Se manejan las colisiones entre la nave Aliada y naves alienigenas.
		// Si estas "intersectan", se produce el final de partida, se carga una nueva imagen de fondo
		// indicando "final de partida" y la nave aliada desaparece de la pantalla.
		Rectangle rAliada = aliada.getBounds();
		Rectangle rAlienigenaA = alienigenaA.getBoundsA();

		Rectangle rAlienigenaB = alienigenaB.getBoundsB();

		if (rAliada.intersects(rAlienigenaA)) {
			ImageIcon i = new ImageIcon(this.getClass().getResource("e xplosionNAVE.gif"));
			Alienigena.imageA = i.getImage();
			ImageIcon ii = new ImageIcon(this.getClass().getResource(" naveExpl.gif"));
			Aliada.image = ii.getImage();

			ImageIcon iii = new ImageIcon(this.getClass().getResource( "gameover.jpg"));
			imagen = iii.getImage();
			// Se desactiva la imagen contador de naves eliminadas.
			contNaves = null;
          	aliada.x = -9000;
          	aliada.y = -9000;

          	desactEnter = true;
          }

        if (rAliada.intersects(rAlienigenaB)) {
			ImageIcon iii = new ImageIcon(this.getClass().getResource( "explosionNAVE.gif"));
			Alienigena.imageB = iii.getImage();

			ImageIcon i = new ImageIcon(this.getClass().getResource("g ameover.jpg"));
			imagen = i.getImage();
			contNaves = null;
			aliada.x = -9000;
          	aliada.y = -9000;

          	desactEnter = true;
        }

        Toolkit.getDefaultToolkit().sync();
     	g.dispose();
     }

    // Método para manejar el avance de los elementos en ejecucion.
	public void actionPerformed(ActionEvent e) {
		if (vuelta2 == true) {
			muertes = 0;
          	aliada.x = 40;
          	aliada.y = 250;

          	ImageIcon ii = new ImageIcon(this.getClass().getResource(" universe2.gif"));
          	imagen = ii.getImage();

          	ImageIcon iii = new ImageIcon(this.getClass().getResource( "aliada.png"));
          	Aliada.image = iii.getImage();

          	alienEliminados = "x0.jpg";
			ImageIcon iiii = new ImageIcon(this.getClass().getResource (alienEliminados));
            contNaves = iiii.getImage();

            vuelta2 = false;
        }

        ArrayList lsr = aliada.getLasers();
        // Mediante la llamada al metodo isVisible de la clase laser, se define si el laser avanza una
        // posicion o si por el contrario debe desaparecer de la pantalla.
        for (int i = 0; i < lsr.size(); i++) {
        	Laser ls = (Laser) lsr.get(i);
			if (ls.isVisible())
				ls.move();
			else lsr.remove(i);

			// El disparo laser desaparece cuando colisiona con una na ve alienigena, sino avanza una posicion.
			if (colision == false) {
			     ls.move();
			}

			if (colision == true) {
			    lsr.remove(i);
			    colision = false;
			}
		}

		// La nave aliada avanza una posicion.
	    aliada.move();
		// La nave alienigena tipo A avanza una posicion.
	    alienigenaA.moveA();
		// La nave alienigena tipo B avanza una posicion.
	    alienigenaB.moveB();
	    // Se llama a este metodo para volver a pintar los elementos e n su nueva posicion a cada golpe de timer.
	    repaint();
	}

	// Metodo para actualizar la imagen contador de naves eliminadas s egun el numero de naves
	// alienigenas eliminadas.
	// Si se alcanza el limite indicado en cada modo de juego, se carg a una nueva imagen de fondo
	// indicando que la partida ha sido ganada y colocando las naves a lienigenas fuera de pantalla.
	public void eliminados() {
	    switch (muertes) {
        	case 1:
        		alienEliminados = "x1.jpg";
           		break;
	        case 2:
	            alienEliminados = "x2.jpg";
	            break;
	        case 3:
	            alienEliminados = "x3.jpg";
	            break;
	        case 4:
	            alienEliminados = "x4.jpg";
	            break;
	        case 5:
	            alienEliminados = "x5.jpg";
	            break;
	        case 6:
	           alienEliminados = "x6.jpg";
	            break;
	        case 7:
	            alienEliminados = "x7.jpg";
	            break;
	        case 8:
	            alienEliminados = "x8.jpg";
	            break;
	        case 9:
	            alienEliminados = "x9.jpg";
	            break;
			case 10:
				if (maxMuertes == 10 && JuegoNuevo.modoJuego == 1) {
					ImageIcon i = new ImageIcon(this.getClass().getResource("youwin.jpg"));
					imagen = i.getImage();
					Alienigena.xA=3000;
					Alienigena.xB=3000;
					Alienigena.seMueve = false;
					Alienigena.dx=0;
					Alienigena.dy=0;
					alienEliminados = "aliensad.jpg";

					desactEnter = true;
				}else
                	alienEliminados = "x10.jpg";
           		break;
           	case 11:
           		alienEliminados = "x11.jpg";
           		break;
      		case 12:
           		alienEliminados = "x12.jpg";
           		break;
      		case 13:
           		alienEliminados = "x13.jpg";
           		break;
           	case 14:
 				alienEliminados = "x14.jpg";
				break;
			case 15:
				if (maxMuertes == 15 && JuegoNuevo.modoJuego == 2) {
					ImageIcon i = new ImageIcon(this.getClass().getResource("youwin.jpg")); imagen = i.getImage();
					Alienigena.xA=3000;
					Alienigena.xB=3000;
					Alienigena.seMueve = false;
					Alienigena.dx=0;
					Alienigena.dy=0;
					alienEliminados = "aliensad.jpg";
					
					desactEnter = true;
				}else
					alienEliminados = "x15.jpg";
				break;
			case 16:
			    alienEliminados = "x16.jpg";
			    break;
			case 17:
			    alienEliminados = "x17.jpg";
			    break;
			case 18:
			    alienEliminados = "x18.jpg";
			    break;
			case 19:
			    alienEliminados = "x19.jpg";
			    break;
			case 20:
				if (maxMuertes == 20 && JuegoNuevo.modoJuego == 3) {
					ImageIcon i = new ImageIcon(this.getClass().getResurce("youwin.jpg")); imagen = i.getImage();
					Alienigena.xA=3000;
					Alienigena.xB=3000;
					Alienigena.seMueve = false; Alienigena.dx=0;
					Alienigena.dy=0;
					alienEliminados = "aliensad.jpg";
					
					desactEnter = true;
				}else
					alienEliminados = "x20.jpg";
				break;
			case 21:
			    alienEliminados = "x21.jpg";
			    break;
			case 22:
			    alienEliminados = "x22.jpg";
			    break;
			case 23:
			    alienEliminados = "x23.jpg";
			    break;
			case 24:
			    alienEliminados = "x24.jpg";
			    break;
			case 25:
			    alienEliminados = "x25.jpg";
			    break;
			case 26:
			    alienEliminados = "x26.jpg";
			    break;
			case 27:
			    alienEliminados = "x27.jpg";
			    break;
			case 28:
			    alienEliminados = "x28.jpg";
			    break;
			case 29:
			    alienEliminados = "x29.jpg";
			    break;
			case 30:
				if (maxMuertes == 30 && JuegoNuevo.modoJuego == 4) {
					ImageIcon i = new ImageIcon(this.getClass().getResource("youwin.jpg"));
					imagen = i.getImage();
					Alienigena.xA=3000;
					Alienigena.xB=3000;
					Alienigena.seMueve = false;
					Alienigena.dx=0;
					Alienigena.dy=0;
					alienEliminados = "aliensad.jpg";

					desactEnter = true;
				}else
 					alienEliminados = "x30.jpg";
				break;
		}
	}

	// Clase interna TAdapter, se encarga de manejar las acciones pulsar/soltar una tecla.
	private class TAdapter extends KeyAdapter {
		public void keyReleased(KeyEvent e) {
       		aliada.keyReleased(e);
  		}

  		public void keyPressed(KeyEvent e) {
  			aliada.keyPressed(e);
		}
	}
}