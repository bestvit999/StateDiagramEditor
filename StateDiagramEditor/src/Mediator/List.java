package Mediator;

public class List extends Component {

	public List(Mediator _mediator) {
		super(_mediator);
	}

	public void changeCurrentFont(FontDialog d) {
		super.mediator.componentsChanged("List.changeCurrentFont", d);
	}

	public void changeCurrentStyle(FontDialog d) {
		super.mediator.componentsChanged("List.changeCurrentStyle", d);
	}

}
