using System.Collections;
using System.Collections.Generic;
using UnityEngine;

public class CameraScript : MonoBehaviour
{
    public float left, right;
    public GameObject mario;
    // Start is called before the first frame update
    void Start()
    {
        
    }

    // Update is called once per frame
    void Update()
    {
        var marioX = mario.transform.position.x;
        var marioY = mario.transform.position.y;
        var camX = transform.position.x;
        var camY = transform.position.y;

        if(marioX > left && marioX < right){
            camX = marioX;
        }else{
            if(camX <left) camX = left;
            if(camX >right) camX = right;
        }
        transform.position = new Vector3(camX, camY, -10);
        
    }
}
