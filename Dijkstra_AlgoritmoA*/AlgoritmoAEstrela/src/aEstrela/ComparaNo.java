package aEstrela;

import java.util.Comparator;

public class ComparaNo implements Comparator<No>{
	public int compare(No n1, No n2) {
		if (n1.getCustoF() < n2.getCustoF()) return -1;
		else if (n1.getCustoF() > n2.getCustoF()) return +1;
        else return 0;
    }

}
