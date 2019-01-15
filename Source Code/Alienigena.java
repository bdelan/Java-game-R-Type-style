/************************************************************************************************
* Clase Alienígena. 																			*
* --------------------------------------------------------------------------------------------- *
* En esta clase se definen los atributos de las diferentes naves alienigenas, movimiento y se 	*
* trata el manejo estas. 																		*
* <<< Borja Delgado Angulo >>> 																	*
***********************************************************************************************/

// Lista de bibliotecas.
import java.awt.Image;
import java.awt.Rectangle;

import javax.swing.ImageIcon;

// Clase Alienígena.
public class Alienigena {
    // Atributos.
	// Posicion.
    public static int xA, yA, xB, yB;
    // Avance.
    public static int dx;
    public static int dy;
    // Medidas del objeto.
	public int widthA, heightA, widthB, heightB;
	// Guardar posicion en caso de colision. public static int avanzaA;
	public static int avanzaB;

	// Valida si una nave alienigena ha sido eliminada.
	public boolean muertoA;
	public boolean muertoB;
	// Valida si se debe crear una nueva nave alienigena, para no suma r al contador cuando lo sea necesario
    public boolean nuevoA = true;
    public boolean nuevoB = true;

    public static boolean seMueve = true;

    //Variable para las imagenes de las naves alienigenas.
    public static Image imageA;
    private Image imageAlienA;
    public static Image imageB;
    private Image imageAlienB;

    // Constructor de la clase Alienigena.
	public Alienigena() {
		// Se recibe la imagen de la nave Alienigena tipo A de una imagen externa.
		ImageIcon ii = new ImageIcon(this.getClass().getResource("spac eshipA.gif"));
		// Se obtienen las medidas de la imagen de la nave Alienigena tipo A.
		imageA = ii.getImage();
		imageAlienA = ii.getImage();

		// Se recibe la imagen de la nave Alienigena tipo B de una imagen externa.
		ImageIcon iii = new ImageIcon(this.getClass().getResource("spa ceshipB.gif"));
		imageB = iii.getImage();
		imageAlienB = iii.getImage();
		// Se obtienen las medidas de las imagenes de las naves Alienigenas tipo A y B.
		widthA  = imageA.getWidth(null);
		heightA = imageA.getHeight(null);
		widthB  = imageB.getWidth(null);
		heightB = imageB.getHeight(null);

		// Se define el origen de las naves alienigenas al comienzo de l juego.
	    xA = 800;
	    yA = 100;
	    xB = 800;
	    yB = 300;
	}

	// Metodo para el movimiento de las Naves tipo A.
	public void moveA() {
		// Hasta que no comience una nueva partida, la nave alienigena A se queda sin moverse.
		if (seMueve == true) {
			// La nave alienigena tipo B avanza una posicion.
			xA -= dx+1;
			// Si la nave alienigena tipo A llega un poco mas lejos de l limite izquierdo de la pantalla,
			//esta aparece de nuevo por el lado derecho en una posicion diferente en el eje "y".
			if ((getXA() > -500) && (getXA() <= -50)) {
				xA = 1000;
	            yA = yA+182;
	            if (yA >= 510 || yA <= 50) {
					yA = 100;
				}
	           	
	           	imageA = imageAlienA;
	      	}

	      	// Si la nave alienigena tipo A es eliminada, esta avanza unas posiciones en el eje "x" e "y",
	      	// para dar tiempo a que se cargue la imagen de explosion.
	      	// Despues, esta aparece de nuevo por el lado derecho en una posicion diferente en el eje "y",
	      	// con la imagen recargada de nuevo, como si se tratase de una nueva nave alienigena.

	      	if (muertoA) {
	      		if (xA < avanzaA-300) {
	      			xA += 1000;
					yA += 182;
					if (yA >= 510 && yA <= 50) {
					     yB = yB+92;
					}else yB = 100;

					imageA = imageAlienA;
					muertoA = false;
					nuevoA = true;
				}
			}
		}
	}

	// Metodo para devolver la posicion en la que la nave alienigena ti po A es eliminada.
	public void muertoA() {
	    avanzaA = xA;
	    muertoA = true;
	}

	// Obtenemos la posición de la nave alienigena tipo A en ese moment o en el eje X.
	public int getXA() {
	    return xA;
	}

	// Obtenemos la posición de la nave alienigena tipo A en ese moment o en el eje Y.
	public int getYA() {
	    return yA;
	}

	// Metodo para obtener la imagen de la nave alienigena tipo A.
	public Image getImageA() {
	    return imageA;
	}

	// Metodo para obtener los limites de la imagen de la nave alienig ena tipo A.
	public Rectangle getBoundsA() {
		return new Rectangle(xA, yA, widthA-10, heightA-10);
	}

	// Metodo para el movimiento de las Naves tipo B.
	public void moveB() {
		// Hasta que no comience una nueva partida, la nave alienigena B se queda sin moverse.
		if (seMueve == true) {
			// Se controla que si la nave allienigena tipo B llega a u no de los limites de la pantalla
			// en el eje "y", esta cambia el sentido.
			if (getYB() >= 510) {
			    dy = -2;
			}

			if (getYB() <= 50) {
			    dy = 2;
			}

			// La nave alienigena tipo B avanza una posicion.
			xB -= dx;
			yB += dy;

			// Si la nave alienigena tipo B llega un poco mas lejos de l limite izquierdo de la 
			// pantalla, esta aparece de nuevo por el lado derecho en una posicion diferente
			// en el eje "y".
			if ((getXB() > -500) && (getXB() <= -50)) {
			    xB = 1000;
			    if (yB <= 550) {
			        yB = yB+92;
			    }else yB = 10;

			    imageB = imageAlienB;
			}

			// Si la nave alienigena tipo B es eliminada, esta avanza unas posiciones en el
			// eje "x", para dar tiempo a que se cargue la imagen de explosion.
			// Despues, esta aparece de nuevo por el lado derecho en u na posicion diferente en el
			// eje "y", con la imagen recargada de nuevo, como si se t ratase de una nueva nave alienigena.
			if (muertoB) {
                dy = 0;
                if (xB < avanzaB-150) {
                	xB += 1000;
					dy = 2;
					if (yB <= 550) {
					    yB = yB+92;
					}else yB = 10;

					imageB = imageAlienB;
					muertoB = false;
					nuevoB = true;
				}
			}
		}
	}

	// Metodo para devolver la posicion en la que la nave alienigena ti po B es eliminada.
	public void muertoB() {
	    avanzaB = xB;
	    muertoB = true;
	}

	// Obtenemos la posición de la nave alienigena tipo B en ese moment o en el eje X.
	public int getXB() {
	    return xB;
	}

	// Obtenemos la posición de la nave alienigena tipo B en ese moment o en el eje Y.
	public int getYB() {
	    return yB;
	}

	// Metodo para obtener la imagen de la nave alienigena tipo B.
	public Image getImageB() {
	    return imageB;
	}

	// Metodo para obtener los limites de la imagen de la nave alienige na tipo B.
	public Rectangle getBoundsB() {
		return new Rectangle(xB, yB, widthB-10, heightB);
	}
}