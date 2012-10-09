package tools;

import java.util.ArrayList;
import lib.IElement;

public class Combine implements IElement {
	private float v[] = new float[0];
	private float c[] = new float[0];
	private int indices[] = new int[0];
	private int VertexDataSieze;
	
	public Combine(ArrayList<IElement> elementList) {
		
		int before = 0;
		for (IElement e: elementList) {
			this.v = ac(this.v, e.get_v());
			this.c = ac(this.c, e.get_c());
			this.indices = ac(this.indices, e.get_indices(), before);
			before = this.v.length / 3;
		}
		
		this.VertexDataSieze = this.v.length / 3;
	}
	
	private float[] ac(float[] a, float[] b) {
		int sa = a.length;
		int sb = b.length;
		float ret[] = new float[sa+sb];
		for (int i = 0; i < sa; i++) {
			ret[i] = a[i];
		}
		for (int i = sa; i < sa+sb; i++) {
			ret[i] = b[i-sa];
		}
		return ret;
	}
	
	private int[] ac(int[] a, int[] b, int before) {
		int sa = a.length;
		int sb = b.length;
		int ret[] = new int[sa+sb];
		for (int i = 0; i < sa; i++) {
			ret[i] = a[i];
		}
		for (int i = sa; i < sa+sb; i++) {
			ret[i] = b[i-sa] + before;
		}
		return ret;
	}

	@Override
	public float[] get_v() {
		return this.v;
	}

	@Override
	public float[] get_c() {
		return this.c;
	}

	@Override
	public int[] get_indices() {
		return this.indices;
	}

	@Override
	public int get_VertexDataSize() {
		return this.VertexDataSieze;
	}

}
