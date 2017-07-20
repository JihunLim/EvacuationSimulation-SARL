package EvacGUI.Behaviors;

import io.sarl.lang.annotation.SarlElementType;
import io.sarl.lang.annotation.SarlSpecification;
import io.sarl.lang.annotation.SyntheticMember;
import io.sarl.lang.core.Event;
import org.eclipse.xtext.xbase.lib.Pure;

@SarlSpecification("0.5")
@SarlElementType(13)
@SuppressWarnings("all")
public class SimulStart extends Event {
  public final int mode;
  
  public SimulStart(final int mode) {
    this.mode = mode;
  }
  
  @Override
  @Pure
  @SyntheticMember
  public boolean equals(final Object obj) {
    if (this == obj)
      return true;
    if (obj == null)
      return false;
    if (getClass() != obj.getClass())
      return false;
    SimulStart other = (SimulStart) obj;
    if (other.mode != this.mode)
      return false;
    return super.equals(obj);
  }
  
  @Override
  @Pure
  @SyntheticMember
  public int hashCode() {
    int result = super.hashCode();
    final int prime = 31;
    result = prime * result + this.mode;
    return result;
  }
  
  /**
   * Returns a String representation of the SimulStart event's attributes only.
   */
  @SyntheticMember
  @Pure
  protected String attributesToString() {
    StringBuilder result = new StringBuilder(super.attributesToString());
    result.append("mode  = ").append(this.mode);
    return result.toString();
  }
  
  @SyntheticMember
  private final static long serialVersionUID = 595070504L;
}
