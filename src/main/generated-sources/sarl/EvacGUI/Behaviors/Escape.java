package EvacGUI.Behaviors;

import io.sarl.lang.annotation.SarlElementType;
import io.sarl.lang.annotation.SarlSpecification;
import io.sarl.lang.core.AgentTrait;
import io.sarl.lang.core.Capacity;

@FunctionalInterface
@SarlSpecification("0.5")
@SarlElementType(17)
@SuppressWarnings("all")
public interface Escape extends Capacity {
  public abstract void Escape();
  
  public static class ContextAwareCapacityWrapper<C extends Escape> extends Capacity.ContextAwareCapacityWrapper<C> implements Escape {
    public ContextAwareCapacityWrapper(final C capacity, final AgentTrait caller) {
      super(capacity, caller);
    }
    
    public void Escape() {
      try {
        ensureCallerInLocalThread();
        this.capacity.Escape();
      } finally {
        resetCallerInLocalThread();
      }
    }
  }
}
