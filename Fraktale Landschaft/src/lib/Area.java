package lib;

public class Area implements IElement {
	private float v[];
	private float c[];
	private int indices[];
	private int VertexDataSieze;
	
	public Area(float a, float[] color) {
		float d = a/2;
		
		float vC[] = {d,d,0,   d,-d,0,   -d,-d,0,   -d,d,0};
		
		float cC[] = new float[12];
		for (int i = 0; i < 12; i+=3) {
			cC[i] = color[0];
			cC[i+1] = color[1];
			cC[i+2] = color[2];
		}
		
		int indicesC[] = {0,3,2, 2,1,0};
		
		this.v = vC;
		this.c = cC;
		this.indices = indicesC;
		this.VertexDataSieze = this.v.length / 3;
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
