package se.feomedia.assignment.component;

import com.artemis.Component;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.utils.Array;

public class SnakePosition extends Component {

    public Array<Vector2> positionArray = new Array<Vector2>();

    public SnakePosition() {

        for (int i = 0; i < 10000; i++)
            positionArray.add(new Vector2());
    }
}