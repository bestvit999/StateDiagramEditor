package Mediator;

public class ComboBox extends Component {

	public ComboBox(Mediator _mediator) {
		super(_mediator);
	}

	public void changeCurrentSize(FontDialog d) {
		super.mediator.componentsChanged("ComboBox.changeCurrentSize", d);
	}

}
