using System.Collections;
using System.Collections.Generic;
using UnityEngine;

public class MussroomScript : MonoBehaviour
{
    public  float right, left;
    public new Rigidbody2D rigid;
    public float speed;
    public  bool isRight;
    public Sprite newSprite;
    private bool _isAlive;
    public float speedUp;
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
        float positionX = transform.localPosition.x;
        if(positionX < left ){
            // quay qua phải
                isRight = true;
                }
        else if(positionX > right){
            // quay qua trái
            isRight = false;
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
            GetComponent<BoxCollider2D>().isTrigger = true;
            _isAlive = false;
            //Destroy(gameObject, 1);
        }
    }
}


}
