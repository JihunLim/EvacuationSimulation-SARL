package EvacGUI.Behaviors;

import io.sarl.lang.annotation.SarlElementType;
import io.sarl.lang.annotation.SarlSpecification;
import io.sarl.lang.core.AgentTrait;
import io.sarl.lang.core.Capacity;

@SarlSpecification("0.5")
@SarlElementType(17)
@SuppressWarnings("all")
public interface Move extends Capacity {
  public abstract void Walk();
  
  public abstract void Run();
  
  public static class ContextAwareCapacityWrapper<C extends Move> extends Capacity.ContextAwareCapacityWrapper<C> implements Move {
    public ContextAwareCapacityWrapper(final C capacity, final AgentTrait caller) {
      super(capacity, caller);
    }
    
    public void Walk() {
      try {
        ensureCallerInLocalThread();
        this.capacity.Walk();
      } finally {
        resetCallerInLocalThread();
      }
    }
    
    public void Run() {
      try {
        ensureCallerInLocalThread();
        this.capacity.Run();
      } finally {
        resetCallerInLocalThread();
      }
    }
  }
}
