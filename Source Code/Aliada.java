/************************************************************************************************
* Clase Aliada. 																				*
* --------------------------------------------------------------------------------------------- *
* En esta clase se definen los atributos de la nave aliada, movimiento y se trata el manejo     *
* de la misma. 																					* 
* <<< Borja DelgadoAngulo >>> 																	* 
*************************************************************************************************/

// Lista de bibliotecas.
import java.awt.Image;
import java.awt.Rectangle;
import java.awt.event.KeyEvent;

import java.util.ArrayList;

import javax.swing.ImageIcon;

// Clase Aliada.
public class Aliada {
	// Atributos.
	// Posicion.
	public int x, y;
	// Avance.
	public int dx, dy;
	// Medidas del objeto.
	private int width, height;
	//Variable para posicionar el origen del disparo laser en la nave.
	private final int naveLaser = 20;

	//Variable para la imagen de la Nave Aliada.
	public static Image image;
	//Variable para el array de lasers creados.
	private ArrayList lasers;

	// Constructor de la clase RType.
	public Aliada() {
		// Se recibe la imagen de la nave Aliada de una imagen externa
		ImageIcon ii = new ImageIcon(this.getClass().getResource("alia da.png")); image = ii.getImage();
		// Se obtienen las medidas de la imagen de la nave Aliada.
		width = image.getWidth(null);
		height = image.getHeight(null);

		// Se define un array para los disparos laser.
		lasers = new ArrayList();

		// Se define el origen de la nave aliada al comienzo del juego

		x = 40;
		y = 250;
	}

	// Metodo para el movimiento de la nave.
     public void move() {
     	// La nave aliada avanza una posicion.
        x += dx;
        y += dy;

        // Se controla que la nave aliada no se salga de los límites q ue se indican.
        if (x <= 10 && x > -1000) {
		     x = 10;
		}
		if (x >= 740) {
		     x = 740;
		}

		if (y <= 50 && y > -1000) {
			y = 50;
		}

		if (y >= 500) {
        	y = 500;
		}
	}

	// Metodo para obtener la posición de la nave en ese momento en el eje X.
	public int getX() {
	     return x;
	}

	// Metodo para obtener la posición de la nave en ese momento en el eje Y.
	public int getY() {
	     return y;
	}

	// Metodo para obtener la imagen de la nave Aliada.
	public Image getImage() {
	     return image;
	}

	// Metodo para obtener la posicion de los lasers en curso.
	public ArrayList getLasers() {
	    return lasers;
	}

	// Metodo para obtener los limites de la imagen de la nave aliada.
	public Rectangle getBounds() {
		return new Rectangle(x, y, width-40, height-40);
	}

	// Metodo para manejar la acción de pulsar una tecla.
	public void keyPressed(KeyEvent e) {
		int key = e.getKeyCode();

		// Tecla Disparo.
	     if (key == KeyEvent.VK_SPACE) {
	        disparar();
		}

		// Tecla movimiento: Izquierda.
		if (key == KeyEvent.VK_A) {
		    dx = -1;
		}

		// Tecla movimiento: Derecha.
     	if (key == KeyEvent.VK_D) {
        	dx = 1;
        }

        // Tecla movimiento: Arriba.
        if (key == KeyEvent.VK_W) {
            dy = -1;
		}

		// Tecla movimiento: Abajo.
        if (key == KeyEvent.VK_S) {
            dy = 1;
		}

		// Tecla nueva partida (Se lanza nueva ventana de seleccion de juego).
		if (key == KeyEvent.VK_ENTER && PantallaJuego.desactEnter) {
			PantallaJuego.desactEnter = false;
            RType.finPartida();
        }

        // Tecla salir de la aplicacion.
        if (key == KeyEvent.VK_ESCAPE) {
            System.exit(0);
		}
	}

	// Método para crear un nuevo laser y posicionar el origen de este en la nave aliada.
	public void disparar() {
		lasers.add(new Laser(x + naveLaser+5, y + naveLaser));
	}

	// Método para manejar la acción de soltar una tecla y detener el movimiento de la nave aliada.
	public void keyReleased(KeyEvent e) {
		int key = e.getKeyCode();
        
        if (key == KeyEvent.VK_A) {
            dx = 0;
		}
        if (key == KeyEvent.VK_D) {
            dx = 0;
		}
        if (key == KeyEvent.VK_W) {
            dy = 0;
		}
        if (key == KeyEvent.VK_S) {
            dy = 0;
		}
	}
}