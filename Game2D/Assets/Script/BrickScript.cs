using System.Collections;
using System.Collections.Generic;
using UnityEngine;
using UnityEngine.Events;
public class BrickScript : MonoBehaviour
{
    public GameObject  boom;
    public UnityEvent _event;
    private void OnCollisionEnter2D(Collision2D other) {
        if(other.gameObject.CompareTag("Player")){
            var direction = other.GetContact(0).normal;
            if(Mathf.Round(direction.y) == 1){
                _event.Invoke();
                GameObject go = Instantiate(boom, transform.position, Quaternion.identity);
                Destroy(go,1);
                Destroy(gameObject,2);
            }
        }
    }
}
