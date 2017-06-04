package org.guanpu.util;

public enum LinodePlans {
	
	LINODE_1024(1),
	LINODE_2048(2),
	LINODE_4096(3),
	LINODE_8192(4),
	LINODE_12288(5),
	LINODE_24576(6),
	LINODE_49152(7),
	Linode_65536(8),
	Linode_81920(9),
	Linode_16384(10),
	Linode_32768(11),
	Linode_61440(12),
	Linode_102400(13),
	Linode_204800(14);
	
	private int value;
	
	private LinodePlans(int value) {
        this.value = value;
    }
	
	public int getValue() {
        return this.value;
    }
}
