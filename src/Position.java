
public class Position {
	private int x;
	private int y;

	public Position() {

	}

	public Position(int x, int y) {
		this.x = x;
		this.y = y;
	}

	public int getX() {
		return x;
	}

	public void setX(int x) {
		this.x = x;
	}

	public int getY() {
		return y;
	}

	public void setY(int y) {
		this.y = y;
	}

	public void incrementY() {
		this.y = (this.y + 1);
	}

	public void incrementX() {
		this.x = (this.x + 1);
	}

	public void decrementY() throws PlanetExplorerException {
		if (y > 0)
			this.y = (this.y - 1);
		else
			throw new PlanetExplorerException();
	}

	public void decrementX() throws PlanetExplorerException {
		if (x > 0)
			this.x = (this.x - 1);
		else
			throw new PlanetExplorerException();
	}

	@Override
	public boolean equals(Object obj) {
		Position onePosition = (Position) obj;
		if (onePosition.getX() == this.x && onePosition.getY() == this.y)
			return true;
		return false;
	}

	@Override
	public String toString() {
		return "(" + this.x + "," + this.y + ")";
	}

}
