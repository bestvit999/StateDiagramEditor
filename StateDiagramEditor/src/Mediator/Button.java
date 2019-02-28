package Mediator;

import java.awt.Dialog;

public class Button extends Component {
	public Button(Mediator _mediator) {
		super(_mediator);
	}

	// set the dialog Visible to false
	public void setFalseVisible(Dialog d) {
		super.mediator.componentsChanged("Button.setFalseVisible", d);
	}

	// set the dialog Visible to Ture
	public void setTrueVisible(Dialog d) {
		super.mediator.componentsChanged("Button.setTrueVisible", d);
	}
}
