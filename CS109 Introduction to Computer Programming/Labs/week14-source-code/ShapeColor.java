
import java.awt.Color;

public enum ShapeColor {
	GREEN(Color.GREEN), RED(Color.RED), GRAY( Color.GRAY);

	private Color color;// The color of instance

	ShapeColor( Color color) {
		this.color = color;
	}
	
	public Color getColor() {
		return this.color;
	}
}
