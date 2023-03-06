using System.Collections;
using System.Collections.Generic;
using UnityEngine;

public class BridgeScript : MonoBehaviour
{
private new Rigidbody2D rigidbody2d;
private float speed;
    // Start is called before the first frame update
    void Start()
    {
        rigidbody2d = GetComponent<Rigidbody2D>();   
    }

    // Update is called once per frame
    void Update()
    {
        rigidbody2d.velocity = new Vector2(speed, 0);
    }
    public  void setSpeed(float value){
        speed = value;
    }
}
