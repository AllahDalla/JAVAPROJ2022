package p2.interfaces;

import p2.event.Event;
import p2.event.*;

public interface OpenClose {
	Event open();

	Event close();

	boolean isOpen();
}
