package se.feomedia.assignment.component;

import com.artemis.Component;
import com.badlogic.gdx.math.Vector2;

public class Movement extends Component {

    public float speed = 300f;
    public Vector2 direction = new Vector2(1,1).nor();
}