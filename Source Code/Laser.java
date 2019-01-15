/************************************************************************************************
* Clase Laser. 																					*
* ------------------------------------------------------------------------------------------- 	*
* En esta clase se definen los atributos de los lasers, movimiento y se trata el manejo de 		*
* los mismos. 																					*
* <<< Borja Delgado Angulo >>> 																	*
*************************************************************************************************/

// Lista de bibliotecas.
import java.awt.Image;
import java.awt.Rectangle;

import javax.swing.ImageIcon;

// Clase Laser.
public class Laser {
	// Atributos.
	// Posicion.
	private int x, y;
	// Medidas del objeto.
	public int width, height;
	// Velocidad del disparo laser.
	private final int velocidadLaser = 2;
	
	//Variable para la imagen del laser.
	private Image image;
	

	//Variable para validar si el laser es visible o no.
	boolean visible;
	
	// Constructor de la clase Laser.
	public Laser(int x, int y) {
		// Se recibe la imagen del laser de una imagen externa.
		ImageIcon ii = new ImageIcon(this.getClass().getResource("lase r.jpg"));

		image = ii.getImage();
		// Se obtienen las medidas de la imagen del laser.
		width = image.getWidth(null);
		height = image.getHeight(null);

		// Se define a visible por defecto.
		visible = true;

		// Se define la posicion del laser la que tiene en ese momento

		this.x = x;
		this.y = y;
	}

	// Método para el movimiento del láser.
    public void move() {
        // El laser avanza una posicion.
        x += velocidadLaser;

        //Si el laser pasa del limite izquierdo de la pantalla, este d eja de verse.
        if (x > 810){
            visible = false;
		}
	}

	// Metodo para obtener la posición del laser en ese momento en el eje X.
    public int getX() {
        return x;
	}

	// Metodo para obtener la posición del laser en ese momento en el eje Y.
    public int getY() {
        return y;
	}

	// Metodo para obtener la imagen del laser.
    public Image getImage() {
        return image;
	}

	// Metodo para devolver visible al laser.
    public boolean isVisible() {
        return visible;
	}

	// Metodo para obtener los limites de la imagen del laser.
	public Rectangle getBounds() {
		return new Rectangle(x, y, width-5, height-5);
	}
}