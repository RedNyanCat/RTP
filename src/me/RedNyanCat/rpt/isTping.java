package me.RedNyanCat.rpt;

public class isTping {

	static boolean isTping;

	@SuppressWarnings("static-access")
	public isTping(boolean isTping, boolean canTp){

		this.isTping = isTping;

	}

	public static boolean getTping(){

		return isTping;

	}

	public static void setTping(boolean tping){

		isTping = tping;

	}

	static int j = 0;

	public static void setJ(int i){

		j = i;

	}

	public static void addToJ(){

		j++;

	}

	public static int j(){

		addToJ();

		return j;

	}

}
