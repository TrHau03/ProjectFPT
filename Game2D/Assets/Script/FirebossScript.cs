using System.Collections;
using System.Collections.Generic;
using UnityEngine;

public class FirebossScript : MonoBehaviour
{
   private new Rigidbody2D rigidbody2d;
    public float speed;
    // Start is called before the first frame update
    void Start()
    {
        Rotation();
        rigidbody2d = GetComponent<Rigidbody2D>();   
        rigidbody2d.velocity = new Vector2(speed, 0);
        Destroy(gameObject, 2);
    }
    private void OnTriggerEnter2D(Collider2D other) {
        if (other.gameObject.CompareTag("sangach"))
        {
            Destroy(gameObject);
        }
    }
    public void setSpeed(float value){
        speed = value;
    }
    public void Rotation(){
        Vector2 scale = transform.localScale;
        if(speed > 0) scale.x *=  scale.x > 0 ? 1: -1;
        else   scale.x *=  scale.x > 0 ? -1: 1;
        
        transform.localScale = scale;
    }
}
