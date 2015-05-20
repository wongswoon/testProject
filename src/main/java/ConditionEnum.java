/**
 * Created by Lenovo on 15-4-24.
 * 搜索条件枚举
 */
public enum ConditionEnum {

    MAX_3_STOPS {
        @Override
        public boolean isGoal(Param param) {
            return param.getStart() == param.getEnd() && param.getDepth() > 1 && param.getDepth() <= 3;
        }

        @Override
        public boolean isFailed(Param param) {
            return param.getDepth() > 3;
        }
    },
    EXACT_4_STOPS {
        @Override
        public boolean isGoal(Param param) {
            return param.getStart() == param.getEnd() && param.getDepth() == 4;
        }

        @Override
        public boolean isFailed(Param param) {
            return param.getDepth() > 4;
        }
    },
    DIST_LESSTHAN_30 {
        @Override
        public boolean isGoal(Param param) {
            return param.getStart() == param.getEnd() && param.getDepth() > 1 && param.getDist() < 30;
        }

        @Override
        public boolean isFailed(Param param) {
            return param.getDist() >= 30;
        }
    };


    public abstract boolean isGoal(Param param);

    public abstract boolean isFailed(Param param);
}
