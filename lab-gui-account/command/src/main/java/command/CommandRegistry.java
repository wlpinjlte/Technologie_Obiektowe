package command;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class CommandRegistry {

	private ObservableList<Command> executedCommandStack = FXCollections
			.observableArrayList();
	private ObservableList<Command> undoCommandStack = FXCollections
			.observableArrayList();

	public void executeCommand(Command command) {
		command.execute();
		executedCommandStack.add(command);
	}

	public void redo() {
		if(!undoCommandStack.isEmpty()){
			Command command= undoCommandStack.get(undoCommandStack.size()-1);
			undoCommandStack.remove(undoCommandStack.size()-1);
			command.redo();
			executedCommandStack.add(command);
		}
	}

	public void undo() {
		if(!executedCommandStack.isEmpty()){
			Command command= executedCommandStack.get(executedCommandStack.size()-1);
			executedCommandStack.remove(executedCommandStack.size()-1);
			command.undo();
			undoCommandStack.add(command);
		}
	}

	public ObservableList<Command> getCommandStack() {
		return executedCommandStack;
	}
}
