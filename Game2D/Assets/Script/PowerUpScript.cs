using System.Collections;
using System.Collections.Generic;
using UnityEngine;

public class PowerUpScript : MonoBehaviour
{
    public new Rigidbody2D rigid;
    public float speed;
    public float height;
    public Vector2 originalPosition;
    // Start is called before the first frame update
    void Start()
    {
        rigid = GetComponent<Rigidbody2D>();
    }
    private void OnCollisionEnter2D(Collision2D other) {
    if(other.gameObject.CompareTag("Player")){
    GetComponent<BoxCollider2D>().isTrigger = false;
        var direction = other.GetContact(0).normal;
        if(Mathf.Round(direction.y) == -1){

            originalPosition = transform.position;
            StartCoroutine(GoUp());
            GetComponent<BoxCollider2D>().isTrigger = true;
            //Destroy(gameObject, 1);
        }
    }
}
IEnumerator GoUp(){
        while(true){
            transform.position = new Vector2(transform.position.x, transform.position.y+speed*Time.deltaTime);
            if(transform.position.y > originalPosition.y + height) break;
            yield return null;
        }
    }
}
