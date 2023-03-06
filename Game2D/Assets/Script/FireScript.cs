using System.Collections;
using System.Collections.Generic;
using UnityEngine;

public class FireScript : MonoBehaviour
{
    private new Rigidbody2D rigidbody2d;
    private float speed;
    // Start is called before the first frame update
    void Start()
    {
     rigidbody2d = GetComponent<Rigidbody2D>();   
        rigidbody2d.velocity = new Vector2(speed, 0);
        Destroy(gameObject, 3);
    }
    private void OnCollisionEnter2D(Collision2D other) {
        if(other.gameObject.CompareTag("sangach")){
            speed = speed * 0.85f;
            rigidbody2d.velocity = new Vector2(speed, Mathf.Abs(speed));
        }
        if(other.gameObject.CompareTag("boss")){
            Destroy(gameObject);
        }
    }
    public void setSpeed(float value){
        speed = value;
    }
}
