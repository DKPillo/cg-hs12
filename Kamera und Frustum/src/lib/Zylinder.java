package lib;

public class Zylinder implements IElement {
	private float v[];
	private float c[];
	private int indices[];
	private int VertexDataSieze;
	
	public Zylinder(int size, double height, float radius) {
		
		float d = (float) (height/2);
		int parts = size;
		float rad = radius/2;
		
		float front[] = new float[(parts + 1) * 3];
		front[0] = 0;
		front[1] = 0;
		front[2] = d;
		for (int i = 1; i <= parts; i++) {
			int pos = 3*i;
			front[pos + 0] = (float) (Math.cos(Math.toRadians((360 * (i-1)) / parts)) * rad);
			front[pos + 1] = (float) (Math.sin(Math.toRadians((360 * (i-1)) / parts)) * rad);
			front[pos + 2] = d;
		}

		float back[] = new float[(parts + 1) * 3];
		back[0] = 0;
		back[1] = 0;
		back[2] = -d;
		for (int i = 1; i <= parts; i++) {
			int pos = 3*i;
			back[pos + 0] = (float) (Math.cos(Math.toRadians((360 * (i-1)) / parts)) * rad);
			back[pos + 1] = (float) (Math.sin(Math.toRadians((360 * (i-1)) / parts)) * rad);
			back[pos + 2] = -d;
		}
		
		int pos0front = 0;
		int pos0back = (parts+1)*3;
		float vC[] = new float[front.length + back.length];
		for (int i = 0; i < front.length; i++) {
			vC[i] = front[i];
		}
		for (int i = 0; i < back.length; i++) {
			vC[pos0back+i] = back[i];
		}

		float cC[] = new float[vC.length];
		for (int i = 0; i < vC.length; i++) {
			cC[i] = i % 2;
		}

		int indicesC[] = new int[parts * 4 * 3];
		
		int counter_front = 1;
		for (int i = 0*parts; i < 1*parts*3; i+=3) {
			indicesC[i] = pos0front;
			if (i == 1*parts*3 - 3) {
				indicesC[i + 1] = 1;
			} else {
				indicesC[i + 1] = pos0front + counter_front + 1;
			}
			indicesC[i + 2] = pos0front + counter_front;
			counter_front++;
		}

		int counter_center = 0;
		for (int i = 1*parts*3; i < 3*parts*3; i+=6) {
			indicesC[i + 0] = counter_center + 1;
			if (i == 3*parts*3 - 6) {indicesC[i + 1] = pos0back/3 + 1;} else {indicesC[i + 1] = counter_center + pos0back/3 + 2;}
			indicesC[i + 2] = counter_center + pos0back/3 + 1;
			
			if (i == 3*parts*3 - 6) {indicesC[i + 3] = pos0back/3 + 1;} else {indicesC[i + 3] = counter_center + pos0back/3 + 2;}
			indicesC[i + 4] = counter_center + 1;
			if (i == 3*parts*3 - 6) {indicesC[i + 5] = 1;} else {indicesC[i + 5] = counter_center + 2;}
			counter_center++;
		}
		
		int counter_back = 1;
		for (int i = 3*parts*3; i < 4*parts*3; i+=3) {
			indicesC[i] = pos0back/3;
			indicesC[i + 1] = pos0back/3 + counter_back + 0;
			if (i == 4*parts*3 - 3) {
				indicesC[i + 2] = pos0back/3 + 1;
			} else {
				indicesC[i + 2] = pos0back/3 + counter_back + 1;
			}
			counter_back++;
		}
		
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
