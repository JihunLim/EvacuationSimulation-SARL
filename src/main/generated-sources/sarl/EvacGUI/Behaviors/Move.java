package EvacGUI.Behaviors;

import io.sarl.lang.annotation.SarlElementType;
import io.sarl.lang.annotation.SarlSpecification;
import io.sarl.lang.core.AgentTrait;
import io.sarl.lang.core.Capacity;

/**
 * @author User
 */
@FunctionalInterface
@SarlSpecification("0.5")
@SarlElementType(17)
@SuppressWarnings("all")
public interface Move extends Capacity {
  public abstract void speed();
  
  public static class ContextAwareCapacityWrapper<C extends Move> extends Capacity.ContextAwareCapacityWrapper<C> implements Move {
    public ContextAwareCapacityWrapper(final C capacity, final AgentTrait caller) {
      super(capacity, caller);
    }
    
    public void speed() {
      try {
        ensureCallerInLocalThread();
        this.capacity.speed();
      } finally {
        resetCallerInLocalThread();
      }
    }
  }
}
