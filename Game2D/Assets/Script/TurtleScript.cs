using System.Collections;
using System.Collections.Generic;
using UnityEngine;

public class TurtleScript : MonoBehaviour
{
    public  float right, left;
    public new Rigidbody2D rigid;
    public float speed;
    public  bool isRight;
    public Sprite newSprite;
    private bool _isAlive;
    public float height;
    public Vector2 originalPosition;
    // Start is called before the first frame update
    void Start()
    {
        _isAlive = true;
        rigid = GetComponent<Rigidbody2D>();
    }

    // Update is called once per frame
    void Update()
    {
        if(!_isAlive){
            return;
        }
        float positionX = transform.position.x;
        if(positionX < left ){
            // quay qua phải
                isRight = true;
                Vector2 scale = transform.localScale;
                scale.x *= scale.x  < 0 ? 1 : -1;
                transform.localScale = scale;
                }
        else if(positionX > right){
            // quay qua trái
            isRight = false;
             Vector2 scale = transform.localScale;
            scale.x *= scale.x  < 0 ? -1 : 1;
            transform.localScale = scale;
    }
    // Di chuyển turtle
    Vector3  vector3;
    if(isRight){
        vector3 = new Vector3(1, 0, 0);
    }else{
        vector3 = new Vector3(-1, 0, 0);
    }
    transform.Translate(vector3 * speed * Time.deltaTime);
}
private void OnCollisionEnter2D(Collision2D other) {
    if(other.gameObject.CompareTag("Player")){
    GetComponent<BoxCollider2D>().isTrigger = false;
        var direction = other.GetContact(0).normal;
        if(Mathf.Round(direction.y) == -1){
            GetComponent<SpriteRenderer>().sprite = newSprite;
            GetComponent<Animator>().enabled = false;

            originalPosition = transform.position;
            StartCoroutine(GoUp());
            GetComponent<BoxCollider2D>().isTrigger = true;
            _isAlive = false;
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

