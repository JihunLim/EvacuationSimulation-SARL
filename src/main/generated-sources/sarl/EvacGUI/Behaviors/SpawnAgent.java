package EvacGUI.Behaviors;

import io.sarl.lang.annotation.SarlElementType;
import io.sarl.lang.annotation.SarlSpecification;
import io.sarl.lang.annotation.SyntheticMember;
import io.sarl.lang.core.Event;
import org.eclipse.xtext.xbase.lib.Pure;

@SarlSpecification("0.5")
@SarlElementType(13)
@SuppressWarnings("all")
public class SpawnAgent extends Event {
  public int num;
  
  public SpawnAgent(final int _num) {
    this.num = _num;
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
    SpawnAgent other = (SpawnAgent) obj;
    if (other.num != this.num)
      return false;
    return super.equals(obj);
  }
  
  @Override
  @Pure
  @SyntheticMember
  public int hashCode() {
    int result = super.hashCode();
    final int prime = 31;
    result = prime * result + this.num;
    return result;
  }
  
  /**
   * Returns a String representation of the SpawnAgent event's attributes only.
   */
  @SyntheticMember
  @Pure
  protected String attributesToString() {
    StringBuilder result = new StringBuilder(super.attributesToString());
    result.append("num  = ").append(this.num);
    return result.toString();
  }
  
  @SyntheticMember
  private final static long serialVersionUID = 591822859L;
}
