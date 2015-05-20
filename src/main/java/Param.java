/**
 * Created by Lenovo on 15-4-24.
 * 搜索参数
 */
public class Param {
    private int start;
    private int end;
    private int dist;
    private int depth;
    private ConditionEnum conditionEnum;

    public Param(int start, int end, int dist, int depth, ConditionEnum conditionEnum) {
        this.start = start;
        this.end = end;
        this.dist = dist;
        this.depth = depth;
        this.conditionEnum = conditionEnum;
    }

    public int getStart() {
        return start;
    }

    public int getEnd() {
        return end;
    }

    public int getDist() {
        return dist;
    }


    public int getDepth() {
        return depth;
    }


    public ConditionEnum getConditionEnum() {
        return conditionEnum;
    }

    static public class ParamBuilder {
        private int start;
        private int end;
        private int dist;
        private int depth;

        private ConditionEnum conditionEnum;

        public ParamBuilder setStart(int start) {
            this.start = start;
            return this;
        }

        public ParamBuilder setEnd(int end) {
            this.end = end;
            return this;
        }

        public ParamBuilder setDist(int dist) {
            this.dist = dist;
            return this;
        }


        public ParamBuilder setDepth(int depth) {
            this.depth = depth;
            return this;
        }


        public ParamBuilder setCondition(ConditionEnum conditionEnum) {
            this.conditionEnum = conditionEnum;
            return this;
        }

        public Param build() {
            return new Param(start, end, dist, depth, conditionEnum);
        }
    }
}
