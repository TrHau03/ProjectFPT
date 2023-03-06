using System.Collections;
using System.Collections.Generic;
using UnityEngine;

public class Block_Question : MonoBehaviour
{
    private Vector2 originalPosition;
    public Sprite newBlock;
    public float speed, height;
    private bool change;
    public GameObject item1,item2;
    private GameObject newItem;
    // Start is called before the first frame update
    void Start()
    {
        originalPosition = transform.position;
        change = true;
    }
    private void OnCollisionEnter2D(Collision2D collision){
        if(!change) return;
        if(collision.gameObject.CompareTag("Player")){
             var direction = collision.GetContact(0).normal;
        if(Mathf.Round(direction.y) == 1){
            change = false;
            // đổi box
            GetComponent<SpriteRenderer>().sprite = newBlock;
            GetComponent<Animator>().enabled = false;
            //nảy lên
            StartCoroutine(GoUpAndDown());
            //tạo vật phẩm 
            
            int random = Random.Range(0,2);
            Debug.Log(random);
            if(random == 0) {
              newItem = Instantiate<GameObject>(item1);
            }else if (random == 1)
            {
               newItem = Instantiate<GameObject>(item2);
            }else{
                newItem = Instantiate<GameObject>(item2);
            }
            newItem.transform.position = originalPosition;
            StartCoroutine(ItemGoUp(newItem));
        } 
        }
    }
    IEnumerator ItemGoUp(GameObject newItem){
        while(true){
            newItem.transform.position = new Vector2(newItem.transform.position.x, newItem.transform.position.y+speed*Time.deltaTime);
            if(newItem.transform.position.y > originalPosition.y + height) break;
            yield return null;
        }


    }
    IEnumerator GoUpAndDown(){
        while(true){
            transform.position = new Vector2(transform.position.x, transform.position.y+speed*Time.deltaTime);
            if(transform.position.y > originalPosition.y + height) break;
            yield return null;
        }

        while(true){
            transform.position = new Vector2(transform.position.x, transform.position.y - speed*Time.deltaTime);
            if(transform.position.y < originalPosition.y){
                transform.position = originalPosition;
                break;
            }
            yield return null;  
        }
    }

    // Update is called once per frame
    void Update()
    {
        
    }
}
