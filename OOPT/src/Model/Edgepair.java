package Model;

import com.horstmann.violet.product.diagram.sequence.edge.ReturnEdge;
import com.horstmann.violet.product.diagram.sequence.edge.SynchronousCallEdge;

public class Edgepair {
	
	SynchronousCallEdge sync;
	ReturnEdge rt;

	public SynchronousCallEdge getSync() {
		return sync;
	}

	public void setSync(SynchronousCallEdge sync) {
		this.sync = sync;
	}

	public ReturnEdge getRt() {
		return rt;
	}

	public void setRt(ReturnEdge rt) {
		this.rt = rt;
	}

	public Edgepair(SynchronousCallEdge sync) {
		this.sync = sync;
	}
	
}
