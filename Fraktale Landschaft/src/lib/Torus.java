package lib;

public class Torus implements IElement {
	private float v[];
	private float c[];
	private int indices[];
	private int VertexDataSieze;
	private double r_big;
	private double r;
	
	public Torus(int size, double r_big, double r) {
		
		this.r_big = r_big;
		this.r = r;
		
		int array_iterator = 0;
		int array_length = size * size * 3;
		float vC[] = new float[array_length];
		
		for (int vi = 0; vi < size; vi++) {
			
			double local_v = Math.toRadians((360 * vi) / size);
			
			for (int ui = 0; ui < size; ui++) {
				
				double local_u = Math.toRadians((360 * ui) / size);
				
				vC[array_iterator] = (float) x(local_u, local_v);
				vC[array_iterator + 1] = (float) y(local_u, local_v);
				vC[array_iterator + 2] = (float) z(local_u, local_v);
						
				array_iterator += 3;	
			}
		}
		
		
		float cC[] = new float[vC.length];
		for (int i = 0; i < vC.length; i++) {
			cC[i] = i % 2;
		}

		int i_iterator = 0;
		int array_i_iterator = 0;
		int indicesC[] = new int[size * size * 2 * 3];
		for (int i = 0; i < size; i++) {
			for (int j = 0; j < size; j++) {
				if (i == size - 1 && j == size - 1) { //OK
					indicesC[i_iterator] = array_i_iterator;
					indicesC[i_iterator + 1] = size - 1;
					indicesC[i_iterator + 2] = 0;
					indicesC[i_iterator + 3] = 0;
					indicesC[i_iterator + 4] = array_i_iterator - size + 1;
					indicesC[i_iterator + 5] = array_i_iterator;
				} else if (j == size - 1) { //OK
					indicesC[i_iterator] = array_i_iterator;
					indicesC[i_iterator + 1] = array_i_iterator + size;
					indicesC[i_iterator + 2] = array_i_iterator + 1;
					indicesC[i_iterator + 3] = array_i_iterator + 1;
					indicesC[i_iterator + 4] = array_i_iterator + 1 - size;
					indicesC[i_iterator + 5] = array_i_iterator;
				} else if (i == size - 1) { //OK
					indicesC[i_iterator] = array_i_iterator;
					indicesC[i_iterator + 1] = 0 + j;
					indicesC[i_iterator + 2] = 1 + j;
					indicesC[i_iterator + 3] = 1 + j;
					indicesC[i_iterator + 4] = array_i_iterator + 1;
					indicesC[i_iterator + 5] = array_i_iterator;
				} else { //OK
					indicesC[i_iterator] = array_i_iterator;
					indicesC[i_iterator + 1] = array_i_iterator + size;
					indicesC[i_iterator + 2] = array_i_iterator + size + 1;
					indicesC[i_iterator + 3] = array_i_iterator + size + 1;
					indicesC[i_iterator + 4] = array_i_iterator + 1;
					indicesC[i_iterator + 5] = array_i_iterator;
				}
				i_iterator += 6;
				array_i_iterator++;
			}
		}
		
		this.v = vC;
		this.c = cC;
		this.indices = indicesC;
		this.VertexDataSieze = this.v.length / 3;
	}

	private double x(double u, double v) {
		return ((r_big + (r * Math.cos(v))) * Math.cos(u));
	}
	
	private double y(double u, double v) {
		return ((r_big + (r * Math.cos(v))) * Math.sin(u));
	}
	
	private double z(double u, double v) {
		return (r * Math.sin(v));
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
		return VertexDataSieze;
	}

}
