using System.Collections;
using System.Collections.Generic;
using UnityEngine;

public class PirhanaScript : MonoBehaviour
{
    private Vector2 originalPosition ;
    public float speed;
    public float height;
    // Start is called before the first frame update
    void Start()
    {
        originalPosition = transform.position;
        StartCoroutine(GoUp());
    }
    IEnumerator GoUp(){
         while(true){
            transform.position = new Vector2(transform.position.x, transform.position.y+speed*Time.deltaTime);
            if(transform.position.y > originalPosition.y + height) break;
            yield return null;
        }
        StartCoroutine(GoDown());
    }
 IEnumerator GoDown(){
    bool wait = false;
            while(!wait){
                wait = true;
                yield return new WaitForSeconds(2);
            }
        while(true){
            transform.position = new Vector2(transform.position.x, transform.position.y - speed*Time.deltaTime);
            if(transform.position.y < originalPosition.y){
                transform.position = originalPosition;
                break;
            }
            yield return null;  
        }
        StartCoroutine(GoUp());
    }
    // Update is called once per frame

}
