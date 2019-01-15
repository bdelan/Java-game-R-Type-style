/************************************************************************************************
* Clase JuegoNuevo.																				*
* -------------------------------------------------------------------------------------------- 	*
* Se definen los detalles de la ventana de juego y se carga el limite de muertes alienigenas 	*
* segun el modo de juego seleccionado 															*
* <<< Borja Delgado Angulo >>> 																	*
************************************************************************************************/

// Lista de bibliotecas.
import javax.swing.JFrame;

// Clase JuegoNuevo.
public class JuegoNuevo extends JFrame {
	//Atributos.
     public static int modoJuego;

    // Constructor de la clase JuegoNuevo.
    public JuegoNuevo() {
        add(new PantallaJuego());

        // Se activa la opcion de salir de la aplicacion al pulsar el boton de salir(x) de la ventana
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		// Tamaño de la ventana de juego.
		setSize(800, 600);
		// Se establece la posicion de la ventana en el centro.
		setLocationRelativeTo(null);
		// Se desactiva la opcion de modificar el tamaño de la ventana
		setResizable(false);
		// Se activa la visibilidad de la ventana.
		setVisible(true);

		// Dependiendo del valor pasado en la clase RType, se cargan l os titulos y limite de naves a eliminar correspondientes.
		switch (modoJuego) {
		    case 1:
				setTitle("R - Type <<< Modo Facil >>>");
				PantallaJuego.maxMuertes = 10;
				break;
			case 2:
				setTitle("R - Type <<< Modo Normal >>>");
				PantallaJuego.maxMuertes = 15;
				break;
			case 3:
				setTitle("R - Type <<< Modo Complicado >>>");
		        PantallaJuego.maxMuertes = 20;
		        break;
		    case 4:
		        setTitle("R - Type <<< Modo IMPOSIBLE >>>");
		        PantallaJuego.maxMuertes = 30;
				break;
		}
	}

	// Método para cargar una partida nueva.
	public static void main(String[] args) {
		JuegoNuevo juegoNuevo = new JuegoNuevo();
	}
}