/************************************************************************************************
* Clase RType. 																					*
* --------------------------------------------------------------------------------------------- *
* Clase principal de la aplicación java. 														*
* Se definen los detalles de la pantalla principal de la aplicacion (tamaño, títulos, modos de 	*
* juego) y se carga la partida seleccionada por el jugador. 									*
* <<< Borja Delgado Angulo >>> 																	*
***********************************************************************************************/

// Lista de bibliotecas.
import javax.swing.JFrame;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.ImageIcon;

import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

// Clase RType.
public class RType extends JFrame implements ActionListener {
	// Atributos.
	// Etiquetas para el texto y la imagen de la ventana.
    JLabel texto, imag;
    // Botones de seleccion de la ventana principal.
	JButton botonFacil, botonNormal, botonComplicado, botonImposible, botonSalir;

	public static boolean vuelta = true;

	// Constructor de la clase RType.
	public RType() {
		setTitle("Practica POO 2013: R-Type");
		// Tamaño de la ventana principal.
		setBounds(500,200,250,450);
		// Contenedor que pone los elementos en linea.
		setLayout (new FlowLayout());
		// Se desactiva la opcion de modificar el tamaño de la ventana
		setResizable(false);

		// Se definen los elementos que contendra el contenedor.
		// Imagen de la ventana principal.
		imag = new JLabel(new ImageIcon(getClass().getResource("imagenPrincipal.jpg")));

		// Etiqueta con texto seleccion.
		texto = new JLabel("Seleccionar dificultad");

		// Botones de seleccion.
		botonFacil = new JButton("Facil (10 enemigos)");
		botonNormal = new JButton("Normal (15 enemigos)");
		botonComplicado = new JButton("Complicado (20 enemigos)");
		botonImposible = new JButton(" IMPOSIBLE (30 enemigos) ");
		botonSalir = new JButton("Salir");

		// Se añaden los elementos una vez definidos.
		botonFacil.addActionListener (this);
		botonNormal.addActionListener (this);
		botonComplicado.addActionListener (this);
		botonImposible.addActionListener (this);
		botonSalir.addActionListener (this);

		add(imag);
		
		add(texto);

		add(botonFacil);
		add(botonNormal);
		add(botonComplicado);
		add(botonImposible);
		add(botonSalir);

		// Se activa la opcion de salir de la aplicacion al pulsar el boton de salir(x) de la ventana.
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}

	// Metodo para manejar el boton pulsado.
	// Segun la seleccion, se cargaran las opciones definidas por el m odo de juego (cantidad y
	// velocidad de las naves alienigenas) o se sale de la aplicacion.
	public void actionPerformed (ActionEvent evento) {
		if (evento.getSource() == botonFacil) {
			JuegoNuevo.modoJuego = 1;
			Alienigena.xA = 800;
			Alienigena.yA = 100;
			Alienigena.xB = 800;
			Alienigena.yB = 300;
			Alienigena.dx = 1;
			Alienigena.dy = 2;
			Alienigena.seMueve = true;
			ImageIcon i = new ImageIcon(this.getClass().getResource("s paceshipB.gif"));
			Alienigena.imageB = i.getImage();
			ImageIcon ii = new ImageIcon(this.getClass().getResource(" spaceshipA.gif"));
			Alienigena.imageA = ii.getImage();
			setVisible(false);

			if (vuelta == true) {
	        	new JuegoNuevo();
	          	vuelta = false;
	     	}else {
	          	PantallaJuego.vuelta2 = true;
	     	}
		}
		else if (evento.getSource() == botonNormal) {
			JuegoNuevo.modoJuego = 2;
			Alienigena.xA = 800;
			Alienigena.yA = 100;
			Alienigena.xB = 800;
			Alienigena.yB = 300;
			Alienigena.dx = 2;
			Alienigena.dy = 3;
			Alienigena.seMueve = true;
			ImageIcon i = new ImageIcon(this.getClass().getResource("s paceshipB.gif"));
			Alienigena.imageB = i.getImage();
			ImageIcon ii = new ImageIcon(this.getClass().getResource(" spaceshipA.gif"));
			Alienigena.imageA = ii.getImage();
			setVisible(false);

			if (vuelta == true) {
		        new JuegoNuevo();
		        vuelta = false;
		    }else {
		    	PantallaJuego.vuelta2 = true;
		    }
		}
		else if (evento.getSource() == botonComplicado) {
			JuegoNuevo.modoJuego = 3;
			Alienigena.xA = 800;
			Alienigena.yA = 100;
			Alienigena.xB = 800;
			Alienigena.yB = 300;
			Alienigena.dx = 4;
			Alienigena.dy = 4;
			Alienigena.seMueve = true;
			ImageIcon i = new ImageIcon(this.getClass().getResource("s paceshipB.gif"));
			Alienigena.imageB = i.getImage();
			ImageIcon ii = new ImageIcon(this.getClass().getResource(" spaceshipA.gif"));
			Alienigena.imageA = ii.getImage();
			setVisible(false);

			if (vuelta == true) {
		        new JuegoNuevo();
		        vuelta = false;
		    }else {
                PantallaJuego.vuelta2 = true;
			}
		}
		else if (evento.getSource() == botonImposible) {
			JuegoNuevo.modoJuego = 4;
			Alienigena.xA = 800;
			Alienigena.yA = 100;
			Alienigena.xB = 800;
			Alienigena.yB = 300;
			Alienigena.dx = 5;
			Alienigena.dy = 5;
			Alienigena.seMueve = true;
			ImageIcon i = new ImageIcon(this.getClass().getResource("s paceshipB.gif"));
			Alienigena.imageB = i.getImage();
			ImageIcon ii = new ImageIcon(this.getClass().getResource(" spaceshipA.gif"));
			Alienigena.imageA = ii.getImage();
			setVisible(false);

			if (vuelta == true) {
                new JuegoNuevo();
                vuelta = false;
            }else {
                PantallaJuego.vuelta2 = true;
            }
        }
		else if (evento.getSource() == botonSalir) {
             System.exit(0);
        }
    }

    // Si la partida finaliza, ya sea por haber vencido o por haber si do derrotado, se carga una nueva
    // pantalla de seleccion.
    public static void finPartida() {
        RType menu = new RType();
        menu.setLocation(500, 200);
        menu.setVisible(true);
	}
	public static void main(String[] args) {
        finPartida();
    }
}