import java.util.ArrayList;
import java.util.List;

// Before submitting write your ID and finish time here. Your ID is written on project description sheets.
// ID: 134
// Finish time: 16:20

public class PlanetExplorer {

	private static final String STRING_DELIMITER = ",";
	private static final String SOUTH_DIRECTION = "S";
	private static final String WEST_DIRECTION = "W";
	private static final String EAST_DIRECTION = "E";
	private static final String NORTH_DIRECTION = "N";
	private static final char LEFT_COMMAND = 'l';
	private static final char RIGHT_COMMAND = 'r';
	private static final char BACKWARD_COMMAND = 'b';
	private static final char FORWARD_COMMAND = 'f';

	Position gridSize;
	List<Position> obstaclesArray = new ArrayList<Position>();
	Position cursorPosition = new Position(0, 0);
	String direction = NORTH_DIRECTION;
	List<Position> foundObstaclesArray = new ArrayList<Position>();

	public PlanetExplorer(int x, int y, String obstacles) throws PlanetExplorerException {
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

	private void convertStringToObstacleArray(String obstacles) throws PlanetExplorerException {
		boolean positionXFilled = false;
		char[] charArray = obstacles.toCharArray();
		Position temp = new Position();
		try {
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
		} catch (Exception e) {
			throw new PlanetExplorerException();
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
			if (oneCommand == FORWARD_COMMAND) {
				moveForward(tempPosition);
			} else if (oneCommand == BACKWARD_COMMAND) {
				moveBackward(tempPosition);
			} else if (oneCommand == RIGHT_COMMAND) {
				moveRight();
			} else if (oneCommand == LEFT_COMMAND) {
				moveLeft();
			}

		}
		return generateExecuteCommandResult();
	}

	private String generateExecuteCommandResult() {
		return "(" + cursorPosition.getX() + STRING_DELIMITER + cursorPosition.getY() + STRING_DELIMITER + direction
				+ ")" + obstacleArrayToString();
	}

	private void moveForward(Position tempPosition) throws PlanetExplorerException {
		if (direction.equals(NORTH_DIRECTION)) {
			cursorPosition.incrementY(gridSize);
			checkAndChangePosition(tempPosition);
		} else if (direction.equals(EAST_DIRECTION)) {
			cursorPosition.incrementX(gridSize);
			checkAndChangePosition(tempPosition);
		} else if (direction.equals(WEST_DIRECTION)) {
			cursorPosition.decrementX();
			checkAndChangePosition(tempPosition);
		} else if (direction.equals(SOUTH_DIRECTION)) {
			cursorPosition.decrementY();
			checkAndChangePosition(tempPosition);
		}
	}

	private void checkAndChangePosition(Position tempPosition) {
		if (checkObstacle(cursorPosition))
			cursorPosition = tempPosition;
	}

	private void moveBackward(Position tempPosition) throws PlanetExplorerException {
		if (direction.equals(NORTH_DIRECTION)) {
			cursorPosition.decrementY();
			checkAndChangePosition(tempPosition);
		} else if (direction.equals(EAST_DIRECTION)) {
			cursorPosition.decrementX();
			checkAndChangePosition(tempPosition);
		} else if (direction.equals(WEST_DIRECTION)) {
			cursorPosition.incrementX(gridSize);
			checkAndChangePosition(tempPosition);
		} else if (direction.equals(SOUTH_DIRECTION)) {
			cursorPosition.incrementY(gridSize);
			checkAndChangePosition(tempPosition);
		}
	}

	private void moveRight() {
		if (direction.equals(NORTH_DIRECTION))
			direction = EAST_DIRECTION;
		else if (direction.equals(EAST_DIRECTION))
			direction = SOUTH_DIRECTION;
		else if (direction.equals(SOUTH_DIRECTION))
			direction = WEST_DIRECTION;
		else if (direction.equals(WEST_DIRECTION))
			direction = NORTH_DIRECTION;
	}

	private void moveLeft() {
		if (direction.equals(NORTH_DIRECTION))
			direction = WEST_DIRECTION;
		else if (direction.equals(EAST_DIRECTION))
			direction = NORTH_DIRECTION;
		else if (direction.equals(WEST_DIRECTION))
			direction = SOUTH_DIRECTION;
		else if (direction.equals(SOUTH_DIRECTION))
			direction = EAST_DIRECTION;
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
