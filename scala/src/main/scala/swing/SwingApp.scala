package swing

import scala.swing._
import scala.swing.event.ButtonClicked

object SwingApp extends SimpleSwingApplication {

  def top = new MainFrame {
    title = "First Swing App"
    val button = new Button("Click me")
    val label = new Label("No button clicks registered")

    listenTo(button)
    var nClicks = 0
    reactions += {
      case ButtonClicked(b) =>
        nClicks += 1
        label.text = "Number of button clicks: "+ nClicks
    }

    contents = new BoxPanel(Orientation.Vertical) {
      contents += button
      contents += label
      border = Swing.EmptyBorder(30, 30, 10, 30)
    }
  }

}