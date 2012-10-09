package lib;

public class Quad implements IElement {
	private float v[];
	private float c[];
	private int indices[];
	private int VertexDataSieze;
	
	public Quad(double xd, double yd, double zd) {
		float x = (float) xd;
		float y = (float) yd;
		float z = (float) zd;
		
		float vC[] = {-x/2,-y/2,z/2,    x/2,-y/2,z/2,   x/2,y/2,z/2,   -x/2,y/2,z/2,
			         -x/2,-y/2,-z/2,   -x/2,-y/2,z/2,   -x/2,y/2,z/2,   -x/2,y/2,-z/2,
				  	 x/2,-y/2,-z/2,   -x/2,-y/2,-z/2,   -x/2,y/2,-z/2,   x/2,y/2,-z/2,
					 x/2,-y/2,z/2,   x/2,-y/2,-z/2,   x/2,y/2,-z/2,   x/2,y/2,z/2,
					 x/2,y/2,z/2,   x/2,y/2,-z/2,   -x/2,y/2,-z/2,   -x/2,y/2,z/2,
					 -x/2,-y/2,z/2,   -x/2,-y/2,-z/2,   x/2,-y/2,-z/2,   x/2,-y/2,z/2};
		
		float cC[] = {1,0,0, 1,0,0, 1,0,0, 1,0,0,
				     0,1,0, 0,1,0, 0,1,0, 0,1,0,
					 1,0,0, 1,0,0, 1,0,0, 1,0,0,
					 0,1,0, 0,1,0, 0,1,0, 0,1,0,
					 0,0,1, 0,0,1, 0,0,1, 0,0,1,
					 0,0,1, 0,0,1, 0,0,1, 0,0,1};
		
		int indicesC[] = {0,2,3, 0,1,2,
						 4,6,7, 4,5,6,
						 8,10,11, 8,9,10,
						 12,14,15, 12,13,14,
						 16,18,19, 16,17,18,
						 20,22,23, 20,21,22};
		
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
