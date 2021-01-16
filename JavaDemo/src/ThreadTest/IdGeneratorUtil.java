package ThreadTest;

import java.util.UUID;

public class IdGeneratorUtil {
	 private static final long START_STMP = 148016465631L;
	    private static final long SEQUENCE_BIT = 4L;
	    private static final long MACHINE_BIT = 2L;
	    private static final long DATA_CENTER_BIT = 2L;
	    private static final long MAX_DATA_CENTER_NUM = 3L;
	    private static final long MAX_MACHINE_NUM = 3L;
	    private static final long MAX_SEQUENCE = 15L;
	    private static final long MACHINE_LEFT = 4L;
	    private static final long DATA_CENTER_LEFT = 6L;
	    private static final long TIMESTMP_LEFT = 8L;
	    private static long dataCenterId;
	    private static long machineId;
	    private static long sequence = 0L;
	    private static long lastStmp = -1L;

	    public IdGeneratorUtil(long datacenterId, long machineId) {
	        if (datacenterId <= 3L && datacenterId >= 0L) {
	            if (machineId <= 3L && machineId >= 0L) {
	                dataCenterId = datacenterId;
	                IdGeneratorUtil.machineId = machineId;
	            } else {
	                throw new IllegalArgumentException("machineId can't be greater than MAX_MACHINE_NUM or less than 0");
	            }
	        } else {
	            throw new IllegalArgumentException("datacenterId can't be greater than MAX_DATACENTER_NUM or less than 0");
	        }
	    }

	    public static synchronized long getKeyId() {
	        long currStmp = getNewstmp();
	        if (currStmp < lastStmp) {
	            throw new RuntimeException("Clock moved backwards.  Refusing to generate id");
	        } else {
	            if (currStmp == lastStmp) {
	                sequence = sequence + 1L & 15L;
	                if (sequence == 0L) {
	                    currStmp = getNextMill();
	                }
	            } else {
	                sequence = 0L;
	            }

	            lastStmp = currStmp;
	            return currStmp - 148016465631L << 8 | dataCenterId << 6 | machineId << 4 | sequence;
	        }
	    }

	    private static long getNextMill() {
	        long mill;
	        for(mill = getNewstmp(); mill <= lastStmp; mill = getNewstmp()) {
	        }

	        return mill;
	    }

	    private static long getNewstmp() {
	        return System.currentTimeMillis();
	    }

	    public static String getUUID() {
	        return UUID.randomUUID().toString().replace("-", "");
	    }

	    public static void main(String[] args) throws Exception {
	        for(int i = 0; i < 50; ++i) {
	            System.out.println(getKeyId());
	        }

	    }
}
