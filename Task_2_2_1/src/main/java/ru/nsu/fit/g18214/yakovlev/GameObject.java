package ru.nsu.fit.g18214.yakovlev;

import javafx.scene.paint.Paint;
import javafx.scene.shape.Rectangle;

public class GameObject extends Rectangle {
  private ObjectType type;
  private Paint defFill;

  void setDefFill(Paint value) {
    defFill = value;
    this.setFill(defFill);
  }

  GameObject(int x, int y) {
    super();
    this.setX(x);
    this.setY(y);
    this.type = ObjectType.EMPTY;
  }

  void setSize(int size) {
    this.setWidth(size);
    this.setHeight(size);
    this.setX(this.getX()*size);
    this.setY(this.getY()*size);
  }

  public boolean equals(Object object) {
    if (!(object instanceof Rectangle)) {
      return false;
    }
    return this.getX() == ((Rectangle) object).getX() && this.getY() == ((Rectangle) object).getY();
  }

  void setType(ObjectType type, Paint paint) {
    this.type = type;
    this.setFill(paint);
  }

  void setType() {
    this.type = ObjectType.EMPTY;
    this.setFill(defFill);
  }

  ObjectType getType() {
    return type;
  }
}

