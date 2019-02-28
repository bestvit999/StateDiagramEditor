package Mediator;

public abstract class Mediator {

	List fontlist, stylelist;
	Label previewLabel;
	ComboBox fontsizelist;
	Button okBT;
	Button cancelBT;
	TextArea textarea;

	public Mediator() {
		fontlist = new List(this);
		stylelist = new List(this);
		previewLabel = new Label(this);
		fontsizelist = new ComboBox(this);
		okBT = new Button(this);
		cancelBT = new Button(this);
		textarea = new TextArea(this);
	}

	// the most important method to call the colleague do something
	// can solve the complex relationship between objects
	public abstract void componentsChanged(String str, Object... objects);
}
