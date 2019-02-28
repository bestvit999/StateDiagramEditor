package Mediator;

//abstract Colleague
public abstract class Component {
	protected Mediator mediator;

	public Component(Mediator _mediator) {
		this.mediator = _mediator;
	}
}
