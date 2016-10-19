import java.util.ArrayList;
import java.util.List;

// Before submitting write your ID and finish time here. Your ID is written on project description sheets.
// ID:
// Finish time:

public class PlanetExplorer {
	Position gridSize;
	List<Position> obstaclesArray = new ArrayList<Position>();
	Position cursorPosition = new Position(0, 0);
	String direction = "N";
	List<Position> foundObstaclesArray = new ArrayList<Position>();

	public PlanetExplorer(int x, int y, String obstacles) {
		/*
		 * x and y represent the size of the grid. Obstacles is a String
		 * formatted as follows:
		 * "(obs1_x,obs1_y)(obs2_x,obs2_y)...(obsN_x,obsN_y)" with no white
		 * spaces.
		 * 
		 * Example use: For a 100x100 grid with two obstacles at coordinates
		 * (5,5) and (7,8) PlanetExplorer explorer = new
		 * PlanetExplorer(100,100,"(5,5)(7,8)")
		 * 
		 */
		gridSize = new Position(x, y);
		convertStringToObstacleArray(obstacles);

	}

	private void convertStringToObstacleArray(String obstacles) {
		boolean positionXFilled = false;
		char[] charArray = obstacles.toCharArray();
		Position temp = new Position();
		for (int loop = 0; loop < charArray.length; loop++) {
			if (isInteger("" + charArray[loop])) {
				if (positionXFilled) {
					positionXFilled = false;
					temp.setY(Integer.parseInt("" + charArray[loop]));
					obstaclesArray.add(temp);
				} else {
					temp = new Position();
					positionXFilled = true;
					temp.setX(Integer.parseInt("" + charArray[loop]));
				}
			}
		}
	}

	public static boolean isInteger(String s) {
		try {
			Integer.parseInt(s);
		} catch (NumberFormatException e) {
			return false;
		} catch (NullPointerException e) {
			return false;
		}
		return true;
	}

	public String executeCommand(String command) throws PlanetExplorerException {

		/*
		 * The command string is composed of "f" (forward), "b" (backward), "l"
		 * (left) and "r" (right) Example: The explorer is on a 100x100 grid at
		 * location (0, 0) and facing NORTH. The explorer is given the commands
		 * "ffrff" and should end up at (2, 2) facing East.
		 * 
		 * The return string is in the format:
		 * "(pos_x,pos_y,facing)(obs1_x,obs1_y)(obs2_x,obs2_y)..(obsN_x,obsN_y)"
		 * Where pos_x and pos_y are the final coordinates, facing is the
		 * current direction the explorer is pointing to (N,S,W,E). The return
		 * string should also contain a list of coordinates of the encountered
		 * obstacles. No white spaces.
		 */
		char[] charArrayofCommands = command.toCharArray();
		Position tempPosition;

		for (char oneCommand : charArrayofCommands) {
			tempPosition = new Position(cursorPosition.getX(), cursorPosition.getY());
			if (oneCommand == 'f') {
				moveForward(tempPosition);
			} else if (oneCommand == 'b') {
				moveBackward(tempPosition);
			} else if (oneCommand == 'r') {
				moveRight();
			} else if (oneCommand == 'l') {
				moveLeft();
			}

		}
		return "(" + cursorPosition.getX() + "," + cursorPosition.getY() + "," + direction + ")"
				+ obstacleArrayToString();
	}

	private void moveForward(Position tempPosition) throws PlanetExplorerException {
		if (direction.equals("N")) {
			cursorPosition.incrementY();
			checkAndChangePosition(tempPosition);
		} else if (direction.equals("E")) {
			cursorPosition.incrementX();
			checkAndChangePosition(tempPosition);
		} else if (direction.equals("W")) {
			cursorPosition.decrementX();
			checkAndChangePosition(tempPosition);
		} else if (direction.equals("S")) {
			cursorPosition.decrementY();
			checkAndChangePosition(tempPosition);
		}
	}

	private void checkAndChangePosition(Position tempPosition) {
		if (checkObstacle(cursorPosition))
			cursorPosition = tempPosition;
	}

	private void moveBackward(Position tempPosition) throws PlanetExplorerException {
		if (direction.equals("N")) {
			cursorPosition.decrementY();
			checkAndChangePosition(tempPosition);
		} else if (direction.equals("E")) {
			cursorPosition.decrementX();
			checkAndChangePosition(tempPosition);
		} else if (direction.equals("W")) {
			cursorPosition.incrementX();
			checkAndChangePosition(tempPosition);
		} else if (direction.equals("S")) {
			cursorPosition.incrementY();
			checkAndChangePosition(tempPosition);
		}
	}

	private void moveRight() {
		if (direction.equals("N"))
			direction = "E";
		else if (direction.equals("E"))
			direction = "S";
		else if (direction.equals("S"))
			direction = "W";
		else if (direction.equals("W"))
			direction = "N";
	}

	private void moveLeft() {
		if (direction.equals("N"))
			direction = "W";
		else if (direction.equals("E"))
			direction = "N";
		else if (direction.equals("W"))
			direction = "S";
		else if (direction.equals("S"))
			direction = "E";
	}

	private String obstacleArrayToString() {
		String result = "";
		for (Position oneObstacle : foundObstaclesArray) {
			result = result + oneObstacle.toString();
		}
		return result;
	}

	private boolean checkObstacle(Position newPosition) {
		for (Position oneObstacle : obstaclesArray) {
			if (oneObstacle.equals(newPosition)) {
				foundObstaclesArray.add(oneObstacle);
				return true;
			}
		}
		return false;
	}
}
