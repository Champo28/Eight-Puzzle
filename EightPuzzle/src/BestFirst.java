import java.util.*;

public class BestFirst {
    protected Queue<State> open;
    private Map<Ilayout,State> close;
    private State actual;
    private Ilayout objective;
    static class State{
        private Ilayout layout;
        private State father;
        private double g;
        public State(Ilayout l, State n){
            layout = l;
            father = n;
            if(father!=null){
                g = father.g + l.getG();
            }
            else{
                g = 0.0;
            }
        }

        public String toString(){
            return layout.toString();
        }
        public double getG(){
            return g;
        }

        public int hashCode(){
            return toString().hashCode();
        }

        public boolean equals (Object o) {
            if (o == null) return false;
            if (this.getClass() != o.getClass()) return false;
            State n = (State) o;
            return this.layout.equals(n.layout);
        }
    }
    private List<State> successors(State n){
        List<State> sucs = new ArrayList<>();
        List<Ilayout> children = n.layout.children();
        for(Ilayout e: children){
            if(n.father == null || !e.equals(n.father.layout)){
                State nn = new State(e,n);
                sucs.add(nn);
            }
        }
        return sucs;
    }

    final public Iterator<State> solve(Ilayout initial, Ilayout goal){
        objective = goal;
        open = new PriorityQueue<>(10, (s1,s2) -> (int) Math.signum(s1.getG()-s2.getG()));
        close = new HashMap<>();
        open.add(new State(initial,null));
        List<State> sucs;
        while(true){
            if(open.isEmpty()){
                return null;
            }
            actual = open.remove();
            if(actual.layout.isGoal(goal)){
                return new solveIterator();
            }
            else{
                sucs = successors(actual);
                close.put(actual.layout, actual);
                for(State s : sucs){
                    if(!open.contains(s) && !close.containsKey(s.layout)){
                        open.add(s);
                    }
                }
            }
        }
    }
    class solveIterator implements Iterator<State>{
        State state;
        Stack<State> stack;

        public solveIterator(){
            state = actual;
            stack = new Stack<>();
            while (state != null){
                stack.push(state);
                state = state.father;
            }
        }
        @Override
        public boolean hasNext() {
            return !stack.isEmpty();
        }

        @Override
        public State next() {
            return stack.pop();
        }
    }
}
